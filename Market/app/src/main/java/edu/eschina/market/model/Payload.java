package edu.eschina.market.model;

import java.io.Serializable;
import java.util.List;

public class Payload  implements Serializable {
    private List<Products> products;
    private String authToken;
    private String totalPrice;

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Payload() {
    }

    public Payload(List<Products> products, String authToken, String totalPrice) {
        this.products = products;
        this.authToken = authToken;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "{" +
                "products=" + products +
                ", authToken='" + authToken + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}
