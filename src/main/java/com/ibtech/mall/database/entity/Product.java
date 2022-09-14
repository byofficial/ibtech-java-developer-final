package com.ibtech.mall.database.entity;

public class Product implements IEntity {
    private long productId;
    private String productName;
    private String imagePath;
    private double salesPrice;
    private long categoryId;

    public Product() {
    }

    public Product(long productId, String productName, String imagePath, double salesPrice) {
        this.productId = productId;
        this.productName = productName;
        this.imagePath = imagePath;
        this.salesPrice = salesPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
