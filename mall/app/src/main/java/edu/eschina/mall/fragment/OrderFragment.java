package edu.eschina.mall.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.eschina.mall.R;
import edu.eschina.mall.adapter.OrderAdapter;
import edu.eschina.mall.databinding.FragmentOrderBinding;
import edu.eschina.mall.model.Order;
import edu.eschina.mall.model.OrderDetail;
import edu.eschina.mall.okhttp.NetworkMode;
import edu.eschina.mall.utils.Config;
import edu.eschina.mall.utils.SuperToast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;



public class OrderFragment extends BaseViewModelFragment<FragmentOrderBinding> {


    @Override
    protected void initListeners() {
        super.initListeners();
        viewBinding.orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SuperToast.show("你点击的是第"+(position+1)+"个订单信息，不过还没我开发");
            }
        });
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        loadNetworkData();
        viewBinding.orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

    private void loadNetworkData() {
        SharedPreferences userSharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String auth_token = userSharedPreferences.getString("auth_token", "");
        String id = userSharedPreferences.getString("id", "");

        FormBody formBody = new FormBody.Builder()
                .add("id", id)
                .add("auth_token", auth_token)
                .build();

        Request request = new Request.Builder()
                .url(Config.ENDPOINT + "/order/listByUserId")
                .post(formBody)
                .build();


        NetworkMode.providerOkHttpClient()
                .newCall(request)
                .enqueue(new Callback() {


                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("OrderFragment", String.valueOf(R.string.server_error));
                        SuperToast.show(R.string.server_error);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String json = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            JSONArray data = jsonObject.getJSONArray("data");
                            List<OrderDetail> orderDetailList = new ArrayList<>();
                            List<Order> orders = new ArrayList<>();
                            for (int i = 0; i < data.length(); i++) {
                                long id = data.getJSONObject(i).getLong("id");
                                String createTime = data.getJSONObject(i).getString("createTime");
                                String updatedTime = data.getJSONObject(i).getString("updatedTime");
                                String version = data.getJSONObject(i).getString("version");
                                boolean valid = data.getJSONObject(i).getBoolean("valid");
                                long orderNo = data.getJSONObject(i).getLong("orderNo");
                                String orderTime = data.getJSONObject(i).getString("orderTime");
                                double totalPrice = data.getJSONObject(i).getDouble("totalPrice");
                                long userId = data.getJSONObject(i).getLong("userId");
                                JSONArray orderDetails = data.getJSONObject(i).getJSONArray("orderDetails");
                                Log.e("order", orderDetails.toString());
                                String productNames = "";
                                for (int j = 0; j < orderDetails.length(); j++) {
                                    long detailId = orderDetails.getJSONObject(j).getLong("id");
                                    String createTime2 = orderDetails.getJSONObject(j).getString("createTime");
                                    String updatedTime2 = orderDetails.getJSONObject(j).getString("updatedTime");
                                    String version2 = orderDetails.getJSONObject(j).getString("version");
                                    boolean valid2 = orderDetails.getJSONObject(j).getBoolean("valid");
                                    String orderId = orderDetails.getJSONObject(j).getString("orderId");
                                    long productId = orderDetails.getJSONObject(j).getLong("productId");
                                    String productNum = orderDetails.getJSONObject(j).getString("productNum");
                                    String productName = orderDetails.getJSONObject(j).getString("productName");
                                    orderDetailList.add(new OrderDetail(detailId, createTime2, updatedTime2, version2, valid2, orderId, productId, productNum, productName));
                                    productNames += j + 1 + "." + orderDetailList.get(j).getProductName();
                                }
                                orders.add(new Order(id, createTime, updatedTime, version, valid, orderNo, orderTime, totalPrice, userId, productNames));
                            }
                            getActivity().runOnUiThread(() -> {
                                OrderAdapter orderAdapter = new OrderAdapter(getContext(), orders);
                                ListView orderList = getView().findViewById(R.id.order_list);
                                orderList.setAdapter(orderAdapter);
                                //默认选择第一个
                                orderList.setSelection(0);
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
