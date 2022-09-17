package com.ibtech.mall.database.entity;

import com.ibtech.mall.database.entity.enums.Status;
import com.ibtech.mall.database.entity.enums.TrendyProduct;

public class Product implements IEntity {
    private long productId;
    private String productName;
    private String imagePath;
    private String description;
    private String longDescription;
    private double salesPrice;
    private long categoryId;
    private Status status;

    private TrendyProduct trendyProduct;

    public Product() {
    }

    public Product(long productId, String productName, String imagePath, double salesPrice, String description, String longDescription) {
        this.productId = productId;
        this.productName = productName;
        this.imagePath = imagePath;
        this.description = description;
        this.longDescription = longDescription;
        this.salesPrice = salesPrice;
    }

    public Product(long productId, String productName, String imagePath, double salesPrice, String description, String longDescription, Status status) {
        this.productId = productId;
        this.productName = productName;
        this.imagePath = imagePath;
        this.description = description;
        this.longDescription = longDescription;
        this.salesPrice = salesPrice;
        this.status = status;
    }

    public Product(long productId, String productName, String imagePath, double salesPrice, String description, String longDescription, Status status, TrendyProduct trendyProduct) {
        this.productId = productId;
        this.productName = productName;
        this.imagePath = imagePath;
        this.description = description;
        this.longDescription = longDescription;
        this.salesPrice = salesPrice;
        this.status = status;
        this.trendyProduct = trendyProduct;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TrendyProduct getTrendyProduct() {
        return trendyProduct;
    }

    public void setTrendyProduct(TrendyProduct trendyProduct) {
        this.trendyProduct = trendyProduct;
    }
}
