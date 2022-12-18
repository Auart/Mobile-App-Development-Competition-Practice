package edu.eschina.market.activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import java.util.Objects;
import edu.eschina.market.database.ShoppingDBHelper;
import edu.eschina.market.databinding.ActivityProductDetailsBinding;
import edu.eschina.market.model.Commodity;
import edu.eschina.market.utils.Config;
import edu.eschina.market.utils.LoadImageTask;

public class ProductDetailsActivity extends BaseViewModelActivity<ActivityProductDetailsBinding> {

    private Commodity commodity;

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
        commodity = (Commodity) getIntent().getSerializableExtra("commodity");
        viewBinding.productPrice.setText("￥"+commodity.getProductPrice());
        viewBinding.productName.setText(commodity.getProductName());
        viewBinding.productDesc.setText(commodity.getDescription());
        new LoadImageTask(viewBinding.productImage).execute(Config.IMAGE_URL+ commodity.getProductImage());
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        //把指定商品添加到购物车
        viewBinding.addCart.setOnClickListener((v)->{
            ShoppingDBHelper shoppingDBHelper = new ShoppingDBHelper(this);
            SQLiteDatabase writableDatabase = shoppingDBHelper.getWritableDatabase();
            String sqlStr="replace into shopping (_id,name,description,price,pic) values (?,?,?,?,?);";
            writableDatabase.execSQL(sqlStr,new Object[]{commodity.getId(),commodity.getProductName(),commodity.getDescription(),commodity.getProductPrice(),commodity.getProductImage()});
            writableDatabase.close();
            Toast.makeText(this,"成功添加到购物车",Toast.LENGTH_SHORT).show();
        });
    }
}
