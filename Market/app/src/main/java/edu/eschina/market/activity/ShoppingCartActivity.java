package edu.eschina.market.activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

import edu.eschina.market.MyApplication;
import edu.eschina.market.adapter.ShoppingCartAdapter;
import edu.eschina.market.database.ShoppingDBHelper;
import edu.eschina.market.databinding.ActivityShoppingCartBinding;
import edu.eschina.market.model.Commodity;
import edu.eschina.market.model.Payload;
import edu.eschina.market.model.Products;
import edu.eschina.market.utils.Config;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ShoppingCartActivity extends BaseViewModelActivity<ActivityShoppingCartBinding> {
    private ArrayList<Commodity> commodityList;

    @Override
    protected void initViews() {
        super.initViews();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        super.initData();
        ShoppingDBHelper shoppingDBHelper = new ShoppingDBHelper(this);
        SQLiteDatabase db = shoppingDBHelper.getReadableDatabase();

        if(db.isOpen()){
            Cursor cursor = db.rawQuery("select * from shopping;", null);
            // 创建用户对象的集合
            commodityList = new ArrayList<>();

            // 遍历查询结果并将数据封装到用户对象中
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("product_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String pic = cursor.getString(cursor.getColumnIndex("pic"));
                commodityList.add(new Commodity(id, name, description, price, pic));
            }
            // 关闭 Cursor 对象
            cursor.close();
            db.close();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //将数据展示到页面上,并刷新
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(this, commodityList);
        viewBinding.cartList.setAdapter(shoppingCartAdapter);
        viewBinding.cartList.setSelection(0);
        for (int i = 0; i < commodityList.size(); i++) {
            MyApplication.getInstance().price+= Float.parseFloat(commodityList.get(i).getProductPrice());
        }
        viewBinding.initPrice.setText("￥" + MyApplication.getInstance().price);
        viewBinding.totalPrice.setText("￥" + MyApplication.getInstance().price);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        viewBinding.settle.setOnClickListener(v -> addOrder());
    }
    private void addOrder() {

        ArrayList<Products> products = new ArrayList<>();
        if (commodityList.size() != 0) {
            for (int i = 0; i < commodityList.size(); i++) {
                products.add(new Products(Long.parseLong(commodityList.get(i).getId()), 1));
            }
            SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
            String auth_token = user.getString("auth_token", "");
            Payload payload = new Payload();
            payload.setProducts(products);
            payload.setAuthToken(auth_token);
            payload.setTotalPrice(String.valueOf(MyApplication.getInstance().price));
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            try {
                for (int i = 0; i < products.size(); i++) {
                    jsonArray.put(products.get(i));
                }
                jsonObject.put("products", jsonArray);
                jsonObject.put("auth_token", auth_token);
                jsonObject.put("totalPrice", MyApplication.getInstance().price);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String json = jsonObject.toString().replaceAll("\\\\", "")
                    .replaceAll("\\}\"", "}")
                    .replaceAll("\"\\{", "{");
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder()
                    .url(Config.ENDPOINT + "/order/add")
                    .method("POST", body)
                    .addHeader("Content-type", "application/json")
                    .build();
            new OkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String json = response.body().string();
                    Log.e("json", json);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject data = jsonObject.getJSONObject("data");
                        String createTime = data.getString("createTime");
                        runOnUiThread(() -> {
                            Intent intent = new Intent(ShoppingCartActivity.this, SuccessfullyOrderedActivity.class);
                            intent.putExtra("createTime", createTime);
                            startActivity(intent);
                        });
                        ShoppingDBHelper shoppingDBHelper=new ShoppingDBHelper(ShoppingCartActivity.this);
                        SQLiteDatabase db = shoppingDBHelper.getWritableDatabase();
                        if(db.isOpen()){
                            db.execSQL("delete from shopping");
                            db.close();
                        }
                        MyApplication.getInstance().price=0;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ShoppingCartActivity.this, "结算失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
