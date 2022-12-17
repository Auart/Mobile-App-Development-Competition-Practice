package edu.eschina.market.fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.eschina.market.adapter.OrderAdapter;
import edu.eschina.market.databinding.FragmentOrderBinding;
import edu.eschina.market.model.Order;
import edu.eschina.market.utils.Config;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OrderFragment extends BaseViewModelFragment<FragmentOrderBinding> {

    private SharedPreferences sharedPreferences;

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        super.initView();
    }
    @Override
    protected void initData() {
        super.initData();
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String auth_token = sharedPreferences.getString("auth_token", "");
        String id = sharedPreferences.getString("id", "");
        loadNetwork(id, auth_token);
    }
    private void loadNetwork(String id, String token) {
        FormBody formBody = new FormBody.Builder()
                .add("id", id)
                .add("auth_token", token)
                .build();
        Request request = new Request.Builder()
                .url(Config.ENDPOINT + "/order/listByUserId")
                .post(formBody)
                .build();
        new OkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    private String message;
                    private String json;
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        try {
                            json = response.body().string();
                            Log.e("HomeFragment",json);
                            JSONObject jsonObject = new JSONObject(json);
                            JSONArray data = jsonObject.getJSONArray("data");
                            message = jsonObject.getString("message");
                            List<Order> orderList = new ArrayList<>();
                            for (int i = 0; i < data.length(); i++) {
                                String orderNo = data.getJSONObject(i).getString("orderNo");
                                String orderTime = data.getJSONObject(i).getString("orderTime");
                                JSONArray orderDetails = data.getJSONObject(i).getJSONArray("orderDetails");
                                String orderNames = "";
                                for (int j = 0; j < orderDetails.length(); j++) {
                                    String productName = orderDetails.getJSONObject(j).getString("productName");
                                    orderNames +=j+1+"."+productName;
                                }
                                orderList.add(new Order(orderNo, orderNames, orderTime));
                            }
                            getActivity().runOnUiThread(() -> {
                                OrderAdapter orderAdapter = new OrderAdapter(getContext(), orderList);
                                viewBinding.orderList.setAdapter(orderAdapter);
                                viewBinding.orderList.setSelection(0);
                            });
                        } catch (JSONException e) {
                            getActivity().runOnUiThread(()-> Toast.makeText(getContext(),message,Toast.LENGTH_SHORT));
                            e.printStackTrace();
                        }
                    }
                });
    }
    @Override
    protected void initEvents() {
        super.initEvents();
    }
}
