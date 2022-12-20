package edu.eschina.market.activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;
import edu.eschina.market.R;
import edu.eschina.market.adapter.BottomNavigateAdapter;
import edu.eschina.market.databinding.ActivityMainBinding;
import edu.eschina.market.databinding.BottomNavLayoutBinding;
import edu.eschina.market.fragment.HomeFragment;
import edu.eschina.market.fragment.MeFragment;
import edu.eschina.market.fragment.OrderFragment;
public class MainActivity extends BaseViewModelActivity<ActivityMainBinding> {

    private SharedPreferences userSharedPreferences;
    private LinearLayout navHome;
    private LinearLayout navOrder;
    private LinearLayout navMe;
    private TextView tvHome;
    private TextView tvOrder;
    private TextView tvMe;
    private ImageView ivHome;
    private ImageView ivOrder;
    private ImageView ivMe;

    @SuppressLint("CutPasteId")
    @Override
    protected void initViews() {
        super.initViews();
        navHome = findViewById(R.id.nav_home);
        navOrder = findViewById(R.id.nav_order);
        navMe = findViewById(R.id.nav_me);
        tvHome = findViewById(R.id.tv_home);
        tvOrder = findViewById(R.id.tv_order);
        tvMe = findViewById(R.id.tv_me);
        ivHome = findViewById(R.id.iv_home);
        ivOrder = findViewById(R.id.iv_order);
        ivMe = findViewById(R.id.iv_me);
    }

    @Override
    protected void initData() {
        super.initData();
        userSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String auth_token = userSharedPreferences.getString("auth_token", null);
        if (auth_token == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
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
        navHome.setOnClickListener((view) -> viewBinding.viewpager.setCurrentItem(0));
        navOrder.setOnClickListener((view) -> viewBinding.viewpager.setCurrentItem(1));
        navMe.setOnClickListener((view) -> viewBinding.viewpager.setCurrentItem(2));

        //viewPageChange
        viewBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)     {
                pageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pageSelected(0);
    }

    private void resetColor() {
        ivHome.setSelected(false);
        ivOrder.setSelected(false);
        ivMe.setSelected(false);
        tvHome.setTextColor(getResources().getColor(R.color.black));
        tvOrder.setTextColor(getResources().getColor(R.color.black));
        tvMe.setTextColor(getResources().getColor(R.color.black));
    }

    private void pageSelected(int position) {
        resetColor();
        @SuppressLint("ResourceAsColor") Runnable[] runnable = {
                () -> {
            ivHome.setSelected(true);
            tvHome.setTextColor(getResources().getColor(R.color.colorPrimary));
        },
                () -> {
                    ivOrder.setSelected(true);
                    tvOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
                },
                () ->
                {
                    ivMe.setSelected(true);
                    tvMe.setTextColor(getResources().getColor(R.color.colorPrimary));
                },
        };
        runnable[position].run();
    }
}