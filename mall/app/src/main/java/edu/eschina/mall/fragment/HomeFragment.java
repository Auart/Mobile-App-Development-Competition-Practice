package edu.eschina.mall.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import edu.eschina.mall.R;
import edu.eschina.mall.activity.CommodityDetailsActivity;
import edu.eschina.mall.activity.CommodityListActivity;
import edu.eschina.mall.activity.ShoppingCartActivity;
import edu.eschina.mall.adapter.PopularAdapter;
import edu.eschina.mall.adapter.ProductAdapter;
import edu.eschina.mall.databinding.FragmentHomeBinding;
import edu.eschina.mall.model.Commodity;
import edu.eschina.mall.okhttp.NetworkMode;
import edu.eschina.mall.utils.Config;
import edu.eschina.mall.utils.HashMapProxy;
import edu.eschina.mall.utils.SuperToast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


public class HomeFragment extends BaseViewModelFragment<FragmentHomeBinding> implements View.OnClickListener{
    private ArrayList<Commodity> commodityList;

    private ArrayList<Commodity> commodityList2;

    private static final String TAG = "HomeFragment";

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initDatum() {
        super.initDatum();
        initNetworkData();
    }

    /**
     * 获取网络数据
     */
    public void initNetworkData() {
        Request request = new Request
                .Builder()
                .url(Config.ENDPOINT + "/product/list?indexType=1")
                .get()
                .build();
        NetworkMode
                .providerOkHttpClient()
                .newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e(TAG, "服务器出错");
                        getActivity().runOnUiThread(() -> SuperToast.show("服务器出错"));
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String json = Objects.requireNonNull(response.body()).string();
                        try {
                            commodityList = new ArrayList<Commodity>();
                            JSONArray data = new JSONObject(json).getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                long id = data.getJSONObject(i).getLong("id");
                                String createTime = data.getJSONObject(i).getString("createTime");
                                String updateTime = data.getJSONObject(i).getString("updatedTime");
                                String version = data.getJSONObject(i).getString("version");
                                boolean valid = data.getJSONObject(i).getBoolean("valid");
                                String productName = data.getJSONObject(i).getString("productName");
                                String description = data.getJSONObject(i).getString("description");
                                double price = data.getJSONObject(i).getDouble("price");
                                String pic = data.getJSONObject(i).getString("pic");
                                int indexType = data.getJSONObject(i).getInt("indexType");
                                commodityList.add(new Commodity(id, createTime, updateTime, version, valid, productName, description, price, pic, indexType));
                            }
                            Log.i(TAG, "data" + commodityList);

                            getActivity().runOnUiThread(() -> {
                                ProductAdapter productAdapter = new ProductAdapter(getContext(), commodityList);
                                GridView newProduct = getActivity().findViewById(R.id.new_product);
                                newProduct.setAdapter(productAdapter);
                                //默认选择第一个
                                newProduct.setSelection(0);
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Request request2 = new Request
                .Builder()
                .url(Config.ENDPOINT + "/product/list?indexType=2")
                .get()
                .build();
        NetworkMode
                .providerOkHttpClient()
                .newCall(request2)
                .enqueue(new Callback() {


                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e(TAG, String.valueOf(R.string.server_error));
                        getActivity().runOnUiThread(() -> SuperToast.show(R.string.server_error));
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String json = Objects.requireNonNull(response.body()).string();
                        try {
                            commodityList2 = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(json);
                            JSONArray data = jsonObject.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                long id = data.getJSONObject(i).getLong("id");
                                String createTime = data.getJSONObject(i).getString("createTime");
                                String updateTime = data.getJSONObject(i).getString("updatedTime");
                                String version = data.getJSONObject(i).getString("version");
                                boolean valid = data.getJSONObject(i).getBoolean("valid");
                                String productName = data.getJSONObject(i).getString("productName");
                                String description = data.getJSONObject(i).getString("description");
                                double price = data.getJSONObject(i).getDouble("price");
                                String pic = data.getJSONObject(i).getString("pic");
                                int indexType = data.getJSONObject(i).getInt("indexType");
                                commodityList2.add(new Commodity(id, createTime, updateTime, version, valid, productName, description, price, pic, indexType));
                            }
                            Log.e(TAG, "data2" + commodityList2);
                            getActivity().runOnUiThread(() -> {
                                PopularAdapter popularAdapter = new PopularAdapter(getContext(), commodityList2);
                                GridView recentPopular = getActivity().findViewById(R.id.recent_popular);
                                recentPopular.setAdapter(popularAdapter);
                                //默认选择第一个
                                recentPopular.setSelection(0);
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        viewBinding.tvNewProduct.setOnClickListener(this);
        viewBinding.tvRecentPopular.setOnClickListener(this);
        viewBinding.newProduct.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(getContext(), CommodityDetailsActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("commodity",commodityList.get(position));
            intent.putExtras(bundle);
            Log.e("commodity",commodityList.get(position).toString());
            startActivity(intent);
        });
        viewBinding.recentPopular.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(getContext(), CommodityDetailsActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("commodity",commodityList2.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
        viewBinding.bigImage.setOnClickListener(this);
        viewBinding.actionCart.setOnClickListener(this);
    }


    /**
     * 点击标题
     */
    final HashMapProxy<Integer, Runnable> onClickMap = new HashMapProxy<>();
    @Override
    public void onClick(View v) {
        Objects.requireNonNull(onClickMap.putObject(R.id.tv_new_product, this::onClickCommodityListOfNewTitle)
                .putObject(R.id.tv_recent_popular, this::onClickCommodityListOfPopularTitle)
                .putObject(R.id.big_image,this::onClickBigImage)
                .putObject(R.id.action_cart,this::onClickActionCart)
                .get(v.getId()))
                .run();
    }

    private void onClickActionCart() {
        Intent intent =new Intent(getActivity(), ShoppingCartActivity.class);
        startActivity(intent);
    }

    private void onClickBigImage() {
        SuperToast.show("这个只是张图片");

    }

    private void onClickCommodityListOfNewTitle() {
        Intent intent = new Intent(getContext(), CommodityListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productList", commodityList);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void onClickCommodityListOfPopularTitle() {
        Intent intent = new Intent(getContext(), CommodityListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productList", commodityList2);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
