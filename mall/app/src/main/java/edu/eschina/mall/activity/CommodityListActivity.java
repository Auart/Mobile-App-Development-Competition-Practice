package edu.eschina.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.List;
import edu.eschina.mall.adapter.ProductListAdapter;
import edu.eschina.mall.databinding.ActivityProductListBinding;
import edu.eschina.mall.model.Commodity;

/**
 * 商品列表
 */
public class CommodityListActivity extends BaseTitleActivity<ActivityProductListBinding> {

    private List<Commodity> commodityList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initViews() {
        super.initViews();
    }


    @Override
    protected void initDatums() {
        super.initDatums();
        Bundle bundle = getIntent().getExtras();
        commodityList = (List<Commodity>) bundle.getSerializable("productList");
        Log.e("CommodityListActivity", commodityList.toString());
        ProductListAdapter productListAdapter=new ProductListAdapter(this, commodityList);
        viewBinding.productList.setAdapter(productListAdapter);
        viewBinding.productList.setSelection(0);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        viewBinding.productList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(this,CommodityDetailsActivity.class);
            Bundle bundle=new Bundle();
            Commodity commodity = commodityList.get(position);
            bundle.putSerializable("commodity",commodity);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}

