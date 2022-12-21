package edu.eschina.market.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;

    @Override
    protected void initViews() {
        super.initViews();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        super.initData();
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        commodity = (Commodity) getIntent().getSerializableExtra("commodity");
        viewBinding.productPrice.setText("￥" + commodity.getProductPrice());
        viewBinding.productName.setText(commodity.getProductName());
        viewBinding.productDesc.setText(commodity.getDescription());
        new LoadImageTask(viewBinding.productImage).execute(Config.IMAGE_URL + commodity.getProductImage());
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        //把指定商品添加到购物车
        viewBinding.addCart.setOnClickListener((v) -> {
            String auth_token = sharedPreferences.getString("auth_token", null);
            if (auth_token == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetailsActivity.this);
                builder.setMessage("你还没进行登录,请先登录！")
                        .setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(ProductDetailsActivity.this, LoginActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("取消", (dialog, which) -> {

                        });
                builder.create().show();
            } else {
                ShoppingDBHelper shoppingDBHelper = new ShoppingDBHelper(this);
                SQLiteDatabase db = shoppingDBHelper.getWritableDatabase();

                if (db.isOpen()) {
                    String sqlStr = "replace into shopping (product_id,name,description,price,pic) values (?,?,?,?,?);";
                    db.execSQL(sqlStr, new Object[]{commodity.getId(), commodity.getProductName(), commodity.getDescription(), commodity.getProductPrice(), commodity.getProductImage()});
                    db.close();
                }
                Toast.makeText(this, "成功添加到购物车", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
