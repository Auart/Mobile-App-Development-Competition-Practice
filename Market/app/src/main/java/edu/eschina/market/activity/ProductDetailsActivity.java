package edu.eschina.market.activity;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import java.util.Objects;
import edu.eschina.market.databinding.ActivityProductDetailsBinding;
import edu.eschina.market.model.Commodity;
import edu.eschina.market.utils.Config;
import edu.eschina.market.utils.LoadImageTask;

public class ProductDetailsActivity extends BaseViewModelActivity<ActivityProductDetailsBinding> {

    @Override
    protected void initViews() {
        super.initViews();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        Commodity commodity = (Commodity) getIntent().getSerializableExtra("commodity");
        viewBinding.productPrice.setText(commodity.getProductPrice());
        viewBinding.productName.setText(commodity.getProductName());
        viewBinding.productDesc.setText(commodity.getDescription());
        new LoadImageTask(viewBinding.productImage).execute(Config.IMAGE_URL+commodity.getProductImage());
    }

    @Override
    protected void initEvents() {
        super.initEvents();
    }
}
