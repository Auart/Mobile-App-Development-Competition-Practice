package edu.eschina.mall.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;
import edu.eschina.mall.R;
import edu.eschina.mall.databinding.ActivityRegisterBinding;
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
 * 注册
 */
public class RegisterActivity extends BaseViewModelActivity<ActivityRegisterBinding> implements View.OnClickListener {
    private String TAG = "RegisterActivity";


    @Override
    protected void initDatums() {
        super.initDatums();
        viewBinding.btnRegister.setOnClickListener(this);
        viewBinding.goLogin.setOnClickListener(this);
    }

    final HashMapProxy<Integer, Runnable> onClickMap = new HashMapProxy<>();

    @Override
    public void onClick(View v) {
        Objects.requireNonNull(onClickMap.putObject(R.id.btn_register, this::register)
                .putObject(R.id.go_login, this::goLogin)
                .get(v.getId()))
                .run();
    }

    private void goLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void register() {
        String username = viewBinding.username.getText().toString();
        String password = viewBinding.password.getText().toString();
        String againPassword = viewBinding.againPassword.getText().toString();

        if (password.equals(againPassword)) {

            FormBody formBody = new FormBody.Builder()
                    .add("mobile", username)
                    .add("password", password)
                    .build();
            Request request = new Request.Builder()
                    .url(Config.ENDPOINT + "/user/reg")
                    .post(formBody)
                    .build();
            NetworkMode
                    .providerOkHttpClient()
                    .newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String json = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String message = jsonObject.getString("message");
                                Log.e(TAG, message);
                                runOnUiThread(() -> SuperToast.show(message));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            SuperToast.show("两次输入的密码不正确!");
        }
    }
}
