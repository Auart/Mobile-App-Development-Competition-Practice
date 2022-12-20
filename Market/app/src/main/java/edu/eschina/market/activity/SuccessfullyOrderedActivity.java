package edu.eschina.market.activity;

import android.content.Intent;
import android.os.Bundle;
import edu.eschina.market.databinding.ActivitySuccessfullyOrderedBinding;
public class SuccessfullyOrderedActivity extends BaseViewModelActivity<ActivitySuccessfullyOrderedBinding> {
    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        String createTime = extras.getString("createTime", "");
        viewBinding.orderedTime.setText(createTime);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        viewBinding.backHome.setOnClickListener(v -> startActivity(new Intent(SuccessfullyOrderedActivity.this,MainActivity.class)));
    }
}
