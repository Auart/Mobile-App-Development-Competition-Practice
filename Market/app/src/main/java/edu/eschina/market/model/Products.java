package edu.eschina.market.model;

import java.io.Serializable;

public class Products implements Serializable {
    private long productId;
    private int productNum;

    public Products(long productId, int productNum) {
        this.productId = productId;
        this.productNum = productNum;
    }

    public Products() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "{" +
               '"'+ "productId"+'"'+":" + productId +
               ","+ '"'+"productNum"+'"'+":" + productNum +
                '}';
    }
}
