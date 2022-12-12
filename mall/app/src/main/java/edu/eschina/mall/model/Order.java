package edu.eschina.mall.model;

public class Order extends Common{
    private long orderNo;
    private String orderTime;
    private double totalPrice;
    private long userId;
    private String names;
    public Order() {
    }

    public Order(long id,String createTime, String updateTime, String version, boolean valid, long orderNo, String orderTime, double totalPrice, long userId,String names) {
        super(createTime, updateTime, version, valid);
        super.id=id;
        this.orderNo = orderNo;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.names=names;
    }

    public long getOrderNo() {
        return orderNo;
    }
    public String getNames() {
        return names;
    }
    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", orderTime='" + orderTime + '\'' +
                ", totalPrice=" + totalPrice +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}

