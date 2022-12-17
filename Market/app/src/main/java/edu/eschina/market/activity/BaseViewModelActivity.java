package edu.eschina.market.activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import edu.eschina.market.utils.ViewBindingUtils;
public class BaseViewModelActivity<T extends ViewBinding> extends BaseActivity {
    protected T viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ViewBindingUtils.getViewBinding(getLayoutInflater(), getClass());
        setContentView(viewBinding.getRoot());
    }
}
