package edu.eschina.market.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import edu.eschina.market.utils.ViewBindingUtils;
public abstract class BaseViewModelFragment<T extends ViewBinding > extends BaseFragment {
    protected T viewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding= ViewBindingUtils.getViewBinding(getLayoutInflater(),getClass());
    }

    /**
     * 获取view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View getLayoutInflater(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return viewBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewBinding=null;
    }
}
