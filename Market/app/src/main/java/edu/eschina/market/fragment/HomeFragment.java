package edu.eschina.market.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

import edu.eschina.market.activity.LoginActivity;
import edu.eschina.market.activity.ProductDetailsActivity;
import edu.eschina.market.activity.ProductListActivity;
import edu.eschina.market.activity.ShoppingCartActivity;
import edu.eschina.market.adapter.ProductAdapter;
import edu.eschina.market.database.ShoppingDBHelper;
import edu.eschina.market.databinding.FragmentHomeBinding;
import edu.eschina.market.model.Commodity;
import edu.eschina.market.utils.Config;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class HomeFragment extends BaseViewModelFragment<FragmentHomeBinding> {
    private ArrayList<Commodity> commodityList;
    private ArrayList<Commodity> commodityList2;
    private SharedPreferences sharedPreferences;
    private String auth_token;
    private int count;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        super.initView();

    }
    @Override
    public void onResume() {
        super.onResume();
        ShoppingDBHelper shoppingDBHelper=new ShoppingDBHelper(getActivity());
        SQLiteDatabase db = shoppingDBHelper.getReadableDatabase();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery("select count(*) from shopping; ", null);
            while (cursor.moveToNext()){
                count = cursor.getInt(0);
                Log.e("sql",String.valueOf(count));
            }
            cursor.close();
            db.close();
        }
        viewBinding.toolbar.cartCount.setText(String.valueOf(count));
    }

    @Override
    protected void initData() {
        super.initData();
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        auth_token = sharedPreferences.getString("auth_token", null);
        loadNetwork();


    }


    private void loadNetwork() {
//        OkHttpWrapper okHttpWrapper=new OkHttpWrapper();
//        try {
//            String json = okHttpWrapper.get(Config.ENDPOINT + "/product/list/?indexType=1");
//            Log.e("ok",json);
//            commodityList= new ArrayList<>();
//            JSONObject jsonObject=new JSONObject(json);
//                JSONArray data = jsonObject.getJSONArray("data");
//            Log.e("ok",data.toString());
//                for (int i = 0; i < data.length(); i++) {
//                    commodityList.add(new Commodity(
//                            data.getJSONObject(i).getString("id"),
//                            data.getJSONObject(i).getString("productName"),
//                            data.getJSONObject(i).getString("description"),
//                            data.getJSONObject(i).getString("price"),
//                            data.getJSONObject(i).getString("pic")
//                    ));
//                }
//                requireActivity().runOnUiThread(() -> {
//                    viewBinding.gvNew.setAdapter(new ProductAdapter(getContext(), commodityList));
//                    viewBinding.gvNew.setSelection(0);
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        try {
//            String json = okHttpWrapper.get(Config.ENDPOINT + "/product/list/?indexType=2");
//            commodityList2 = new ArrayList<>();
//            JSONObject jsonObject=new JSONObject(json);
//            JSONArray data = jsonObject.getJSONArray("data");
//            for (int i = 0; i < data.length(); i++) {
//                commodityList2.add(new Commodity(
//                        data.getJSONObject(i).getString("id"),
//                        data.getJSONObject(i).getString("productName"),
//                        data.getJSONObject(i).getString("description"),
//                        data.getJSONObject(i).getString("price"),
//                        data.getJSONObject(i).getString("pic")
//                ));
//            }
//            requireActivity().runOnUiThread(() -> {
//                viewBinding.gvNew.setAdapter(new ProductAdapter(getContext(), commodityList2));
//                viewBinding.gvNew.setSelection(0);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Request request = new Request.Builder()
                .url(Config.ENDPOINT + "/product/list/?indexType=1")
                .get()
                .build();

        new OkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "服务器器出错", Toast.LENGTH_SHORT));
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        commodityList = new ArrayList<>();
                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            JSONArray data = json.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                commodityList.add(new Commodity(
                                        data.getJSONObject(i).getString("id"),
                                        data.getJSONObject(i).getString("productName"),
                                        data.getJSONObject(i).getString("description"),
                                        data.getJSONObject(i).getString("price"),
                                        data.getJSONObject(i).getString("pic")
                                ));
                            }
                            requireActivity().runOnUiThread(() -> {
                                viewBinding.gvNew.setAdapter(new ProductAdapter(getContext(), commodityList));
                                viewBinding.gvNew.setSelection(0);
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Request request2 = new Request.Builder()
                .url(Config.ENDPOINT + "/product/list/?indexType=2")
                .get()
                .build();
        new OkHttpClient().newCall(request2)
                .enqueue(new Callback() {


                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "服务器器出错", Toast.LENGTH_SHORT));
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        commodityList2 = new ArrayList<>();
                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            JSONArray data = json.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                commodityList2.add(new Commodity(
                                        data.getJSONObject(i).getString("id"),
                                        data.getJSONObject(i).getString("productName"),
                                        data.getJSONObject(i).getString("description"),
                                        data.getJSONObject(i).getString("price"),
                                        data.getJSONObject(i).getString("pic")
                                ));
                            }
                            requireActivity().runOnUiThread(() -> {
                                viewBinding.gvPopular.setAdapter(new ProductAdapter(getContext(), commodityList2));
                                viewBinding.gvPopular.setSelection(0);
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }



    @Override
    protected void initEvents() {
        super.initEvents();
        viewBinding.gvNew.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(getContext(), ProductDetailsActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("commodity",commodityList.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
        viewBinding.gvPopular.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(getContext(), ProductDetailsActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("commodity",commodityList2.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
        viewBinding.titleNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ProductListActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("commodityList",commodityList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        viewBinding.titlePopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ProductListActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("commodityList",commodityList2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

      viewBinding.toolbar.actionCart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(auth_token==null){
                  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                  builder.setMessage("你还没进行登录,请先登录！")
                          .setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  startActivity(new Intent(getActivity(), LoginActivity.class));
                                  getActivity().finish();
                              }
                          })
                          .setNegativeButton("取消", (dialog, which) -> {

                          });
                  builder.create().show();

              }else{
                  startActivity(new Intent(getActivity(),ShoppingCartActivity.class));
              }
          }
      });
    }
}
