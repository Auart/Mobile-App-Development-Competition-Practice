package edu.eschina.market.activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import edu.eschina.market.adapter.ProductAdapter;
import edu.eschina.market.databinding.ActivityProductListBinding;
import edu.eschina.market.model.Commodity;
public class ProductListActivity  extends BaseViewModelActivity<ActivityProductListBinding> {

    private ArrayList<Commodity> commodityList;

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initData() {
        super.initData();
        commodityList = (ArrayList<Commodity>) getIntent().getSerializableExtra("commodityList");
        ProductAdapter productAdapter=new ProductAdapter(this, commodityList);
        viewBinding.ltProductList.setAdapter(productAdapter);
        viewBinding.ltProductList.setSelection(0);
    }
    @Override
    protected void initEvents() {
        super.initEvents();
        viewBinding.ltProductList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(this, ProductDetailsActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("commodity",commodityList.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
