package edu.eschina.market.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import edu.eschina.market.adapter.ProductListAdapter;
import edu.eschina.market.databinding.ActivityProductListBinding;
import edu.eschina.market.model.Commodity;
public class ProductListActivity  extends BaseViewModelActivity<ActivityProductListBinding> {

    private ArrayList<Commodity> commodityList;

    @Override
    protected void initViews() {
        super.initViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("商品列表");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        super.initData();
        commodityList = (ArrayList<Commodity>) getIntent().getSerializableExtra("commodityList");
        ProductListAdapter productListAdapter= new ProductListAdapter(this, commodityList);
        viewBinding.ltProductList.setAdapter(productListAdapter);
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
