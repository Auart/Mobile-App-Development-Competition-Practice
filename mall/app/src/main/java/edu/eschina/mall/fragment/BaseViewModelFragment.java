package edu.eschina.mall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import edu.eschina.mall.reflect.ViewBindingUtil;

public abstract class BaseViewModelFragment<T extends ViewBinding> extends BaseLogicFragment {
    protected T viewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
        viewBinding = ViewBindingUtil.getViewBinding(getLayoutInflater(), getClass());
    }

    /**
     * 获取View
     * @param inflater
     * @param viewGroup
     * @param savedFragmentState
     * @return
     */
    @Override
    public View getLayoutInflater(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedFragmentState) {
        return viewBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewBinding = null;
    }
}
