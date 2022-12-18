package edu.eschina.market.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import edu.eschina.market.R;
import edu.eschina.market.model.Commodity;
import edu.eschina.market.utils.Config;
import edu.eschina.market.utils.LoadImageTask;
public class ShoppingCartAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<Commodity> commodityList;

    public ShoppingCartAdapter(Context context, ArrayList<Commodity> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @Override
    public int getCount() {
        return commodityList.size();
    }

    @Override
    public Object getItem(int position) {
        return commodityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_cart, parent, false);
            viewHolder.productName = convertView.findViewById(R.id.product_name);
            viewHolder.productDesc = convertView.findViewById(R.id.product_description);
            viewHolder.productPrice = convertView.findViewById(R.id.product_price);
            viewHolder.productImage = convertView.findViewById(R.id.product_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.productName.setText(commodityList.get(position).getProductName());
        viewHolder.productDesc.setText(commodityList.get(position).getDescription());
        viewHolder.productPrice.setText("￥" + commodityList.get(position).getProductPrice());
        // 加载本地图片并将其转换为 Bitmap 对象
        Log.e("url",Config.IMAGE_URL+commodityList.get(position).getProductImage());
//        new LoadImageTask(viewHolder.productImage).execute(Config.IMAGE_URL+commodityList.get(position).getProductImage());
        Glide.with(convertView).load(Config.IMAGE_URL+commodityList.get(position).getProductImage()).into(viewHolder.productImage);
        return convertView;
    }

    public final class ViewHolder {
        public ImageView productImage;
        public TextView productName;
        public TextView productDesc;
        public TextView productPrice;
    }
}
