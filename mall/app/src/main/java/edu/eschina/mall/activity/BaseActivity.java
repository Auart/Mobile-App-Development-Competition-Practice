package edu.eschina.mall.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();
        initDatums();
        initListeners();

    }

    protected void initListeners() {
    }

    protected void initDatums() {
    }

    protected void initViews() {
    }
}
