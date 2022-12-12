package edu.eschina.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;
import edu.eschina.mall.R;
import edu.eschina.mall.databinding.ActivityLoginBinding;
import edu.eschina.mall.okhttp.NetworkMode;
import edu.eschina.mall.utils.Config;
import edu.eschina.mall.utils.HashMapProxy;
import edu.eschina.mall.utils.SuperToast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 登录
 */
public class LoginActivity extends BaseViewModelActivity<ActivityLoginBinding> implements View.OnClickListener {
    private final String TAG = "LoginActivity";
    private SharedPreferences userSharedPreferences;
    private CheckBox remember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initViews() {
        super.initViews();
    }


    @Override
    protected void initDatums() {
        super.initDatums();
        userSharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        reload();
    }

    private void reload() {
        SharedPreferences useSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        useSharedPreferences.getString("pwd", "");
        viewBinding.username.setText(useSharedPreferences.getString("mobile", ""));
        viewBinding.password.setText(useSharedPreferences.getString("pwd", ""));
        viewBinding.remember.setChecked(useSharedPreferences.getBoolean("isRemember", false));
    }


    @Override
    protected void initListeners() {
        super.initListeners();
        viewBinding.btnLogin.setOnClickListener(this);
        viewBinding.tvRegister.setOnClickListener(this);

    }

    final HashMapProxy<Integer, Runnable> onClickMap = new HashMapProxy<>();

    @Override
    public void onClick(View v) {
        Objects.requireNonNull(onClickMap.putObject(R.id.btn_login, this::login)
                .putObject(R.id.tv_register, this::goRegister)
                .get(v.getId()))
                .run();
    }

    private void goRegister() {
        startActivity(new Intent(this, RegisterActivity.class));

    }

    private void login() {
        String username = viewBinding.username.getText().toString();
        String password = viewBinding.password.getText().toString();

        FormBody formBody = new FormBody.Builder()
                .add("mobile", username)
                .add("pwd", password)
                .build();
        Request request = new Request.Builder()
                .url(Config.ENDPOINT + "/user/login")
                .post(formBody)
                .build();
        NetworkMode.providerOkHttpClient().newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(() -> SuperToast.show(R.string.server_error));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    String json = response.body().string();
                    Log.e("login", json);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject user = data.getJSONObject("user");
                    String id = user.getString("id");
                    String nickName = user.getString("nickName");
                    String headPic = user.getString("headPic");
                    runOnUiThread(() -> {
                        try {
                            if (data.isNull("auth_token")) {
                                SuperToast.show(R.string.username_password_error);
                            } else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                remember = findViewById(R.id.remember);
                                if (remember.isChecked()) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("auth_token", data.getString("auth_token"));
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    SuperToast.show(R.string.login_successful);
                                    userSharedPreferences.edit()
                                            .putString("auth_token", data.getString("auth_token"))
                                            .putString("id", id)
                                            .putString("mobile", username)
                                            .putString("pwd", password)
                                            .putBoolean("isRemember", remember.isChecked())
                                            .apply();
                                }
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
