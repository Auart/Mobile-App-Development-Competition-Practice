package edu.eschina.market.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import edu.eschina.market.activity.LoginActivity;
import edu.eschina.market.databinding.FragmentMeBinding;
public class MeFragment extends BaseViewModelFragment<FragmentMeBinding> {

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvents() {
        viewBinding.logout.setOnClickListener(v -> {
            SharedPreferences user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
            // 删除共享参数中的token
            user.edit().remove("auth_token").apply();
            startActivity(new Intent(getContext(), LoginActivity.class));
            //可以通过调用activity的onBackPressed方法来取消返回一级activity
            getActivity().onBackPressed();
        });
        super.initEvents();
    }
}
