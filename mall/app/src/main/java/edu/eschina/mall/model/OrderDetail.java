package edu.eschina.mall.model;

public class OrderDetail extends Common {
    private String orderId;
    private long productId;
    private String productNum;
    private String productName;

    public OrderDetail(long id ,String createTime, String updateTime, String version, boolean valid,String orderId, long productId, String productNum, String productName) {
        super(createTime, updateTime, version, valid);
        super.id = id;
        this.orderId=orderId;
        this.productId = productId;
        this.productNum = productNum;
        this.productName = productName;
    }

    public OrderDetail() {

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", id=" + id +
                '}';
    }
}
