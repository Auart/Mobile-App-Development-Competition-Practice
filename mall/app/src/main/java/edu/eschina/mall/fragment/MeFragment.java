package edu.eschina.mall.fragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import java.util.Objects;
import edu.eschina.mall.R;
import edu.eschina.mall.activity.LoginActivity;
import edu.eschina.mall.databinding.FragementMeBinding;
import edu.eschina.mall.utils.HashMapProxy;
public class MeFragment extends BaseViewModelFragment<FragementMeBinding> implements View.OnClickListener {

    //fragment实例对象方法
    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initListeners() {
        super.initListeners();
        viewBinding.logout.setOnClickListener(this);
    }


    final HashMapProxy<Integer, Runnable> onClickMap = new HashMapProxy<>();

    @Override
    public void onClick(View v) {
        Objects.requireNonNull(onClickMap.putObject(R.id.logout, this::logout)
                .get(v.getId()))
                .run();
    }

    @SuppressLint("CommitPrefEdits")
    private void logout() {
        SharedPreferences userSharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        //删除token
        userSharedPreferences.edit().remove("auth_token").apply();
        //退出login
        startActivity(new Intent(getActivity(), LoginActivity.class));
        //可以通过调用activity的onBackPressed()方法来取消返回到上一级activity
        getActivity().onBackPressed();
    }
}
