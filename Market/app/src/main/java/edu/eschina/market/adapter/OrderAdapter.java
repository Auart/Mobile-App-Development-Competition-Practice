package edu.eschina.market.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import edu.eschina.market.R;
import edu.eschina.market.model.Order;
public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_order_list,parent,false);
            viewHolder.orderNo = convertView.findViewById(R.id.order_no);
            viewHolder.orderNames = convertView.findViewById(R.id.order_names);
            viewHolder.orderTime= convertView.findViewById(R.id.order_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.orderNo.setText("订单编号："+orderList.get(position).getOrderNo());
        viewHolder.orderNames.setText(orderList.get(position).getOrderNames());
        viewHolder.orderTime.setText("下单时间："+orderList.get(position).getOrderTime());
        return convertView;
}

    public final class ViewHolder {
        public TextView orderNo;
        public TextView orderNames;
        public TextView orderTime;
    }
}
