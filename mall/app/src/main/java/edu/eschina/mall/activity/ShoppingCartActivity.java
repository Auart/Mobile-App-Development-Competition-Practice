package edu.eschina.mall.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import edu.eschina.mall.databinding.ActivityShoppingCartBinding;
import edu.eschina.mall.utils.SuperToast;

/**
 * 购物车
 */
public class ShoppingCartActivity extends BaseTitleActivity<ActivityShoppingCartBinding> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SuperToast.show("从页面仅供展示，抱歉");
    }

    @Override
    protected void initViews() {
        super.initViews();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    protected boolean isShowBackMenu() {
        return true;
    }

    @Override
    protected void initDatums() {
        super.initDatums();
    }


    @Override
    protected void initListeners() {
        super.initListeners();
    }
}
