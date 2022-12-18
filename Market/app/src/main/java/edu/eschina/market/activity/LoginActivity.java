package edu.eschina.market.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;
import edu.eschina.market.databinding.ActivityLoginBinding;
import edu.eschina.market.utils.Config;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends BaseViewModelActivity<ActivityLoginBinding> {
    private static final String TAG = "LoginActivity";
    private SharedPreferences userSharedPreferences;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void initData() {
        super.initData();
        userSharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean isRemember = userSharedPreferences.getBoolean("isRemember", false);
        if (isRemember) {
            viewBinding.inputMobile.setText(userSharedPreferences.getString("mobile", ""));
            viewBinding.inputPassword.setText(userSharedPreferences.getString("pwd", ""));
            viewBinding.rememberPwd.setChecked(true);
        }
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        viewBinding.submitLogin.setOnClickListener(v -> loadNetwork());
        viewBinding.goRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    private void loadNetwork() {
        String mobile = viewBinding.inputMobile.getText().toString();
        String password = viewBinding.inputPassword.getText().toString();
        boolean isRemember = viewBinding.rememberPwd.isChecked();
        FormBody formBody = new FormBody.Builder()
                .add("mobile", mobile)
                .add("pwd", password)
                .build();
        Request request = new Request.Builder()
                .url(Config.ENDPOINT + "/user/login")
                .post(formBody)
                .build();
        Log.e("req",request.toString());
        new OkHttpClient().newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "服务器出错 " + e);
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = Objects.requireNonNull(response.body()).string();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    Log.e(TAG, jsonObject.toString());
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject user = data.getJSONObject("user");
                    String id = user.getString("id");
                    String nickName = user.getString("nickName");
                    String headPic = user.getString("headPic");
                    String token = data.getString("auth_token");
                    runOnUiThread(() -> {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        userSharedPreferences.edit()
                                .putString("auth_token", token)
                                .putString("id",id)
                                .putString("mobile", mobile)
                                .putString("pwd", password)
                                .putBoolean("isRemember", isRemember)
                                .putString("nickName",nickName)
                                .putString("headPic",headPic)
                                .apply();

                    });
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("auth_token", token);
                    startActivity(intent);
                } catch (JSONException e) {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show());
                    e.printStackTrace();
                }
            }
        });
    }
}
