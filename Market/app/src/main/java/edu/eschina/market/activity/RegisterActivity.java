package edu.eschina.market.activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;
import edu.eschina.market.databinding.ActivityRegisterBinding;
import edu.eschina.market.utils.Config;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static android.widget.Toast.LENGTH_SHORT;

public class RegisterActivity extends BaseViewModelActivity<ActivityRegisterBinding> {

    private static final String TAG = "LoginActivity";

    private void loadNetwork() {
        String mobile = viewBinding.inputMobile.getText().toString();
        String password = viewBinding.inputPassword.getText().toString();
        String againPwd = viewBinding.inputAgainPassword.getText().toString();
        if ("".equals(mobile) && "".equals(password) && "".equals(againPwd)) {
            Toast.makeText(this, "用户名与密码不能为空", LENGTH_SHORT).show();
        } else {
            if (password.equals(againPwd)) {
                FormBody formBody = new FormBody.Builder()
                        .add("mobile", mobile)
                        .add("pwd", password)
                        .build();
                Request request = new Request.Builder()
                        .url(Config.ENDPOINT + "/user/reg")
                        .post(formBody)
                        .build();
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
                            final String message = jsonObject.getString("message");
                            runOnUiThread(() -> {
                                Toast.makeText(RegisterActivity.this, message, LENGTH_SHORT).show();
                                if (message.equals("操作成功")) {
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                }
                            });
                        } catch (JSONException e) {
                            runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "注册失败", LENGTH_SHORT).show());
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                Toast.makeText(RegisterActivity.this, "两次密码输入错误，请重新输入", LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        viewBinding.submitLogin.setOnClickListener(v -> loadNetwork());
        viewBinding.goLogin.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
}
