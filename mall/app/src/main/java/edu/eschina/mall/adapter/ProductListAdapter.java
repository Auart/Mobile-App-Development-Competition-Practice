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

public class ProductListAdapter extends BaseAdapter {
    private Context context;
    private List<Commodity> commodityList;

    public ProductListAdapter(Context context, List<Commodity> commodityList) {
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_list, parent, false);
            holder.productImage = convertView.findViewById(R.id.list_product_icon);
            holder.productPrice = convertView.findViewById(R.id.list_product_price);
            holder.productName = convertView.findViewById(R.id.list_product_name);
            holder.productDescription = convertView.findViewById(R.id.list_product_description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(Config.NETWORK_RESOURCE + commodityList.get(position).getPic()).into(holder.productImage);
        holder.productName.setText(commodityList.get(position).getProductName());
        holder.productDescription.setText(commodityList.get(position).getDescription());
        holder.productPrice.setText("￥"+ commodityList.get(position).getPrice());
        return convertView;
    }


    public final static class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView productPrice;
    }
}
