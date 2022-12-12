package edu.eschina.mall.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;
import edu.eschina.mall.R;
import edu.eschina.mall.model.Order;
import edu.eschina.mall.model.OrderDetail;
import edu.eschina.mall.utils.HashMapProxy;

public class OrderAdapter extends BaseAdapter {
    private Context mContext;

    private List<Order> orderList;
    private HashMapProxy<Order,List<OrderDetail>> orderListHashMapProxy;

    public OrderAdapter(Context context, List<Order> orders) {
        this.mContext = context;
        this.orderList = orders;

    }

    @NonNull
    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @SuppressLint({"InflateParams", "ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_order_list, parent,false);
            holder.orderNo = convertView.findViewById(R.id.order_no);
            holder.orderName=convertView.findViewById(R.id.order_names);
            holder.orderTime = convertView.findViewById(R.id.order_time);
            //将视图持有者保存到转换视图当中
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.orderNo.setText("订单编号:" + orderList.get(position).getOrderNo());
        holder.orderName.setText(orderList.get(position).getNames());
        holder.orderTime.setText("下单时间：" + orderList.get(position).getOrderTime());
        return convertView;
    }
    public static final class ViewHolder {
        public TextView orderNo;
        public TextView orderName;
        public TextView orderTime;
    }
}
