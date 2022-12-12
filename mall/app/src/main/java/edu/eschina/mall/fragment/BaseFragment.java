package edu.eschina.mall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    /**
     * 返回显示的控件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater(inflater, container, savedInstanceState);
    }

    protected void initViews() {

    }


    protected void initDatum() {

    }

    protected void initListeners() {

    }

    /**
     * 获取View
     *
     * @param inflater
     * @param viewGroup
     * @param savedFragmentState
     * @return
     */
    public abstract View getLayoutInflater(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedFragmentState);

    /**
     * View创建了
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initDatum();
        initListeners();

    }
}
