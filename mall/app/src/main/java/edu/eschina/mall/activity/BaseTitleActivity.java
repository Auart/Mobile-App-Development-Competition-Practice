package edu.eschina.mall.activity;


import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import java.util.Objects;
import edu.eschina.mall.R;
import edu.eschina.mall.utils.HashMapProxy;

/**
 * 通用标题界面
 *
 * @param <T>
 */
public class BaseTitleActivity<T extends ViewBinding> extends BaseViewModelActivity<T> {
    private Toolbar toolbar;


    @Override
    protected void initViews() {
        super.initViews();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //是否显示返回按钮
        if(isShowBackMenu()){
            showBackMenu();
        }
    }
    //是否显示返回按钮
    protected boolean isShowBackMenu() {
        return false;
    }

    //显示返回按钮
    protected void showBackMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    final HashMapProxy<Integer, Runnable> onOptionItemMap = new HashMapProxy<>();

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Objects.requireNonNull(onOptionItemMap.putObject(android.R.id.home, this::backClick)
                .get(item.getItemId()))
                .run();
        return super.onOptionsItemSelected(item);
    }

    //返回按钮被点击了
    protected void backClick() {
        onBackPressed();
    }
}
