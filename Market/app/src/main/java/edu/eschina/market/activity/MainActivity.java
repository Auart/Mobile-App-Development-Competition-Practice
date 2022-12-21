package edu.eschina.market.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;
import edu.eschina.market.R;
import edu.eschina.market.adapter.BottomNavigateAdapter;
import edu.eschina.market.databinding.ActivityMainBinding;
import edu.eschina.market.fragment.HomeFragment;
import edu.eschina.market.fragment.MeFragment;
import edu.eschina.market.fragment.OrderFragment;

public class MainActivity extends BaseViewModelActivity<ActivityMainBinding> {

    private SharedPreferences userSharedPreferences;
    private edu.eschina.market.databinding.BottomNavLayoutBinding bottomNavLayoutBinding;

    @SuppressLint("CutPasteId")
    @Override
    protected void initViews() {
        super.initViews();
        bottomNavLayoutBinding = viewBinding.bottomNav;
    }

    @Override
    protected void initData() {
        super.initData();
        userSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(OrderFragment.newInstance());
        fragmentList.add(MeFragment.newInstance());
        //获取底导航适配器
        BottomNavigateAdapter bottomNavigateAdapter = new BottomNavigateAdapter(getSupportFragmentManager(), fragmentList);
        //将适配器添加到viewPager中
        viewBinding.viewpager.setAdapter(bottomNavigateAdapter);
    }


    @Override
    protected void initEvents() {
        super.initEvents();

        bottomNavLayoutBinding.navHome.setOnClickListener((view) -> viewBinding.viewpager.setCurrentItem(0));
        bottomNavLayoutBinding.navOrder.setOnClickListener((view) -> viewBinding.viewpager.setCurrentItem(1));
        bottomNavLayoutBinding.navMe.setOnClickListener((view) -> viewBinding.viewpager.setCurrentItem(2));

        //viewPageChange
        viewBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pageSelected(0);
    }

    private void resetColor() {
        bottomNavLayoutBinding.ivHome.setSelected(false);
        bottomNavLayoutBinding.ivOrder.setSelected(false);
        bottomNavLayoutBinding.ivMe.setSelected(false);
        bottomNavLayoutBinding.tvHome.setTextColor(getResources().getColor(R.color.black));
        bottomNavLayoutBinding.tvOrder.setTextColor(getResources().getColor(R.color.black));
        bottomNavLayoutBinding.tvMe.setTextColor(getResources().getColor(R.color.black));
    }

    private void pageSelected(int position) {
        resetColor();
        @SuppressLint("ResourceAsColor") Runnable[] runnable = {
                () -> {
                    bottomNavLayoutBinding.ivHome.setSelected(true);
                    bottomNavLayoutBinding.tvHome.setTextColor(getResources().getColor(R.color.colorPrimary));
                },
                () -> {
                    bottomNavLayoutBinding.ivOrder.setSelected(true);
                    bottomNavLayoutBinding.tvOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
                },
                () ->
                {
                    bottomNavLayoutBinding.ivMe.setSelected(true);
                    bottomNavLayoutBinding.tvMe.setTextColor(getResources().getColor(R.color.colorPrimary));
                },
        };
        runnable[position].run();
    }
}