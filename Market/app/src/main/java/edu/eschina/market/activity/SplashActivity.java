package edu.eschina.market.activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import edu.eschina.market.databinding.ActivitySplashBinding;
public class SplashActivity extends BaseViewModelActivity<ActivitySplashBinding> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.welcome.postDelayed(this::next,2000);
    }

    private void next() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

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

    }
}
