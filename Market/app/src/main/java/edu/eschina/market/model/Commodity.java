package edu.eschina.market.model;
import java.io.Serializable;
public class Commodity implements Serializable {
    private String id;
    private String productName;
    private String description;
    private String productPrice;
    private String productImage;

    public Commodity(String id, String productName, String description, String productPrice, String productImage) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Commodity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
