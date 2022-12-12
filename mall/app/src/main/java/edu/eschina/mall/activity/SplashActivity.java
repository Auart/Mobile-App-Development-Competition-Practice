package edu.eschina.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import edu.eschina.mall.databinding.ActivitySplashBinding;

/**
 * 欢迎页
 */
public class SplashActivity extends BaseViewModelActivity<ActivitySplashBinding> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initViews() {
        super.initViews();
        viewBinding.welcome.postDelayed(this::next,3000);
    }

    private void next() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
