package edu.eschina.mall.activity;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import java.util.Objects;
import edu.eschina.mall.databinding.ActivityProductDetailsBinding;
import edu.eschina.mall.model.Commodity;
import edu.eschina.mall.utils.Config;
import edu.eschina.mall.utils.SuperToast;

/**
 * 商品详情
 */
public class CommodityDetailsActivity extends BaseTitleActivity<ActivityProductDetailsBinding>{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    protected boolean isShowBackMenu() {
        return true;
    }

    @Override
    protected void initViews() {
        super.initViews();
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void initDatums() {
        super.initDatums();
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        Commodity commodity = (Commodity) bundle.getSerializable("commodity");
        Glide.with(this).load(Config.NETWORK_RESOURCE+commodity.getPic()).into(viewBinding.detailImage);
        viewBinding.detailName.setText(commodity.getProductName());
        viewBinding.detailPrice.setText("￥"+ commodity.getPrice());
        viewBinding.detailDescription.setText(commodity.getDescription());
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        viewBinding.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuperToast.show("添加成功");
            }
        });
    }
}
