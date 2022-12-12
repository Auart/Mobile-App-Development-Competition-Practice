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
public class ProductAdapter extends BaseAdapter {

    private Context mContext;
    private List<Commodity> mCommodityList;

    public ProductAdapter(Context context, List<Commodity> commodities) {
        this.mContext = context;
        this.mCommodityList = commodities;
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            //根据布局文件item_product.xml生成转换视图对象
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent,false);
            holder.productName = convertView.findViewById(R.id.product_name);
            holder.productDescribe = convertView.findViewById(R.id.product_describe);
            holder.productPrice = convertView.findViewById(R.id.product_price);
            holder.productImage = convertView.findViewById(R.id.product_image);
            //将视图持有者保存到转换视图当中
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        Commodity commodity = mCommodityList.get(position);
        if (commodity != null) {
            holder.productName.setText(commodity.getProductName());
            holder.productDescribe.setText(commodity.getDescription());
            holder.productPrice.setText("￥" + commodity.getPrice());
            //使用Glide第三方库只要一行代码加载网络图片
            Glide.with(convertView).load(Config.NETWORK_RESOURCE + commodity.getPic()).into(holder.productImage);

            //新建线程加载图片信息，发送到消息队列中
//            new Thread(() -> {
//                Bitmap urLImage = getURLImage(Config.NETWORK_RESOURCE + commodity.getPic());
//                Message message = new Message();
//                message.what = 0;
//                message.obj = urLImage;
//                mHandler.sendMessage(message);
//
//            }).start();

        }

        //在消息队列中实现对控件的更改
//        mHandler = new Handler(Looper.getMainLooper()){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                if(msg.what==0){
//                    holder.productImage.setImageBitmap((Bitmap) msg.obj);
//                }
//                Looper.loop();
//            }
//        };

        return convertView;
    }

    public static final class ViewHolder {
        public ImageView productImage;
        public TextView productName;
        public TextView productDescribe;
        public TextView productPrice;
    }


    //加载图片
//    public Bitmap getURLImage(String url) {
//        Bitmap bmp = null;
//        try {
//
//            URL myurl = new URL(url);
//            // 获得连接
//            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
//            conn.setConnectTimeout(6000);//设置超时
//            conn.setDoInput(true);
//            conn.setUseCaches(false);//不缓存
//            conn.connect();
//            InputStream is = conn.getInputStream();//获得图片的数据流
//            bmp = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bmp;
//
//
//    }
}