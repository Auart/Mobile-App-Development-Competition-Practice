package edu.eschina.market.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import edu.eschina.market.activity.LoginActivity;
import edu.eschina.market.databinding.FragmentMeBinding;
import edu.eschina.market.utils.LoadImageTask;

public class MeFragment extends BaseViewModelFragment<FragmentMeBinding> {

    private SharedPreferences user;
    private String auth_token;

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
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        auth_token = user.getString("auth_token", null);
        if(auth_token==null){
            viewBinding.tvLogout.setText("暂未登录");
        }
        String headPic = user.getString("headPic", "");
        String nickName = user.getString("nickName", null);
        viewBinding.nickName.setText(nickName);
//        new LoadImageTask(viewBinding.headPic).execute(headPic);
//        Glide.with().load(headPic).into(viewBinding.headPic);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        viewBinding.logout.setOnClickListener(v -> {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            if (auth_token == null) {
                alertDialog.setMessage("您目前还没登录确定去登录吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 删除共享参数
                                user.edit().clear()
                                        .apply();
                                startActivity(new Intent(getContext(), LoginActivity.class));
                                //可以通过调用activity的onBackPressed方法来取消返回一级activity
                                getActivity().onBackPressed();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create()
                        .show();
            } else {
                alertDialog.setMessage("确定退出登录吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 删除共享参数
                                user.edit().clear()
                                        .apply();
                                startActivity(new Intent(getContext(), LoginActivity.class));
                                //可以通过调用activity的onBackPressed方法来取消返回一级activity
                                getActivity().onBackPressed();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create()
                        .show();
            }
        });
    }
}
