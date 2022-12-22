package edu.eschina.market.model;


public class Product {
    private String productId;
    private int productNum;

    public Product(String productId, int productNum) {
        this.productId = productId;
        this.productNum = productNum;
    }

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

//    @Override
//    public String toString() {
//        return "{" +
//               '"'+ "productId"+'"'+":" + productId +
//               ","+ '"'+"productNum"+'"'+":" + productNum +
//                '}';
//    }
}
