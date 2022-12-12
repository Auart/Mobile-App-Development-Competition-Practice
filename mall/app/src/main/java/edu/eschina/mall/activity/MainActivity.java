package edu.eschina.mall.activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import edu.eschina.mall.R;
import edu.eschina.mall.adapter.BottomNavigateAdapter;
import edu.eschina.mall.databinding.ActivityMainBinding;
import edu.eschina.mall.fragment.HomeFragment;
import edu.eschina.mall.fragment.MeFragment;
import edu.eschina.mall.fragment.OrderFragment;
import edu.eschina.mall.model.Commodity;
import edu.eschina.mall.utils.HashMapProxy;


/**
 * 主界面
 */
public class MainActivity extends BaseTitleActivity<ActivityMainBinding> implements View.OnClickListener {
    private String TAG = "MainActivity";
    private List<Fragment> fragmentList;
    private ImageView ivHome;
    private ImageView ivOrder;
    private ImageView ivMe;
    private TextView tvMe;
    private TextView tvOrder;
    private TextView tvHome;
    private LinearLayout nav_home;
    private LinearLayout nav_order;
    private LinearLayout nav_me;
    private ArrayList<Commodity> commodityList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = getSharedPreferences("user", MODE_PRIVATE).getString("auth_token", null);
        if (token == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
    @Override
    protected void initViews() {
        super.initViews();
        ivHome = findViewById(R.id.iv_home);
        ivOrder = findViewById(R.id.iv_order);
        ivMe = findViewById(R.id.iv_me);
        tvHome = findViewById(R.id.tv_home);
        tvOrder = findViewById(R.id.tv_order);
        tvMe = findViewById(R.id.tv_me);
        nav_home = findViewById(R.id.bottom_nav_home);
        nav_order = findViewById(R.id.bottom_nav_order);
        nav_me = findViewById(R.id.bottom_nav_me);
    }

    /**
     * 当底部导航栏被点击
     *
     * @param position
     */
    private void onViewPageSelected(int position) {
        resetBottomState();
        switch (position) {
            case 0:
                ivHome.setSelected(true);
                tvHome.setTextColor(getResources().getColor(R.color.bottom));
                break;
            case 1:
                ivOrder.setSelected(true);
                tvOrder.setTextColor(getResources().getColor(R.color.bottom));
                break;
            default:
                ivMe.setSelected(true);
                tvMe.setTextColor(getResources().getColor(R.color.bottom));
        }
    }

    /**
     * 刷新导航状态
     */
    private void resetBottomState() {
        ivHome.setSelected(false);
        tvHome.setTextColor(getResources().getColor(R.color.black));
        ivOrder.setSelected(false);
        tvOrder.setTextColor(getResources().getColor(R.color.black));
        ivMe.setSelected(false);
        tvMe.setTextColor(getResources().getColor(R.color.black));
    }


    @Override
    protected void initDatums() {
        super.initDatums();
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(OrderFragment.newInstance());
        fragmentList.add(MeFragment.newInstance());
        //获取底部导航适配器
        BottomNavigateAdapter bottomNavigateAdapter = new BottomNavigateAdapter(getSupportFragmentManager(), fragmentList);
        //将适配器添加到viewpager中
        viewBinding.viewpager.setAdapter(bottomNavigateAdapter);
        String token = getSharedPreferences("user", MODE_PRIVATE).getString("auth_token", null);
        Log.e("logout", "token" + token);
    }




    @Override
    protected void initListeners() {
        super.initListeners();
        viewBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                onViewPageSelected(position);
            }

            //页面滚动
            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
        //默认进入首页
        onViewPageSelected(0);
        nav_home.setOnClickListener(this);
        nav_order.setOnClickListener(this);
        nav_me.setOnClickListener(this);
    }

    final HashMapProxy<Integer, Runnable> onClickMap = new HashMapProxy<>();

    @Override
    public void onClick(View v) {
        Objects.requireNonNull(onClickMap.putObject(R.id.bottom_nav_home, this::home)
                .putObject(R.id.bottom_nav_order, this::order)
                .putObject(R.id.bottom_nav_me, this::me)
                .get(v.getId()))
                .run();
    }




    private void me() {
        viewBinding.viewpager.setCurrentItem(2);
    }

    private void order() {
        viewBinding.viewpager.setCurrentItem(1);

    }

    private void home() {
        viewBinding.viewpager.setCurrentItem(0);
    }

}