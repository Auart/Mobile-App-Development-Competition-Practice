package edu.eschina.mall.model;


import java.io.Serializable;

public class Commodity  extends Common implements Serializable {

    private String productName;
    private String description;
    private Double price;
    private String pic;
    private int indexType;



    public Commodity() {
    }

    public Commodity(long id,String createTime, String updateTime, String version, boolean valid, String productName, String description, Double price, String pic, int indexType) {
        super(createTime, updateTime, version, valid);
        super.id=id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.pic = pic;
        this.indexType = indexType;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getIndexType() {
        return indexType;
    }

    public void setIndexType(int indexType) {
        this.indexType = indexType;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", indexType=" + indexType +
                ", id=" + id +
                '}';
    }




}
