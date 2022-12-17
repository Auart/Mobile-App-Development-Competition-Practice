package edu.eschina.market.activity;

import android.content.Intent;

import edu.eschina.market.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseViewModelActivity<ActivitySplashBinding> {
    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        viewBinding.welcome.postDelayed(() -> startActivity(new Intent(SplashActivity.this, MainActivity.class)), 2000);
    }
}
