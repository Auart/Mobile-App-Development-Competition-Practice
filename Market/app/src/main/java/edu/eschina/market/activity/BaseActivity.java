package edu.eschina.market.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

     @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();
        initData();
        initEvents();
    }

    protected void initViews(){

    }
    protected void initData(){

    }

    protected void initEvents(){

    }
}
