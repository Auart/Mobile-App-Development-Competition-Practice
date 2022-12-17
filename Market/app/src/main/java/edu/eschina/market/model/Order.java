package edu.eschina.market.model;

public class Order {
    private String orderNo;
    private String orderNames;
    private String orderTime;

    public Order(String orderNo, String orderNames, String orderTime) {
        this.orderNo = orderNo;
        this.orderNames = orderNames;
        this.orderTime = orderTime;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", orderNames='" + orderNames + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNames() {
        return orderNames;
    }

    public void setOrderNames(String orderNames) {
        this.orderNames = orderNames;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
