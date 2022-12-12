package edu.eschina.mall.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import edu.eschina.mall.R;
import edu.eschina.mall.model.Commodity;
import edu.eschina.mall.utils.Config;

public class PopularAdapter extends BaseAdapter {
    private Context mContext;
    private List<Commodity> mCommodityList;

    public PopularAdapter(Context context, List<Commodity> mCommodityList) {
        this.mCommodityList = mCommodityList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mCommodityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommodityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_popular, parent,false);
            holder.productName = convertView.findViewById(R.id.popular_name);
            holder.productDescribe = convertView.findViewById(R.id.popular_describe);
            holder.productPrice = convertView.findViewById(R.id.popular_price);
            holder.productImage = convertView.findViewById(R.id.popular_image);
            //将视图持有者保存到转换视图当中
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Commodity commodity2 = mCommodityList.get(position);
        Glide.with(convertView).load(Config.NETWORK_RESOURCE+commodity2.getPic()).into(holder.productImage);
        holder.productName.setText(commodity2.getProductName());
        holder.productDescribe.setText(commodity2.getDescription());
        holder.productPrice.setText("￥" + commodity2.getPrice());
        return convertView;
    }

    public static final class ViewHolder {
        public ImageView productImage;
        public TextView productName;
        public TextView productDescribe;
        public TextView productPrice;
    }
}
