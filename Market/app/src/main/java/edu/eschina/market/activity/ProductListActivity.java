package edu.eschina.market.activity;

import java.util.ArrayList;
import edu.eschina.market.adapter.ProductAdapter;
import edu.eschina.market.databinding.ActivityProductListBinding;
import edu.eschina.market.model.Commodity;
public class ProductListActivity  extends BaseViewModelActivity<ActivityProductListBinding> {
    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initData() {
        super.initData();
        ArrayList<Commodity> commodityList = (ArrayList<Commodity>) getIntent().getSerializableExtra("commodityList");
        ProductAdapter productAdapter=new ProductAdapter(this,commodityList);
        viewBinding.ltProductList.setAdapter(productAdapter);
        viewBinding.ltProductList.setSelection(0);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
    }
}
