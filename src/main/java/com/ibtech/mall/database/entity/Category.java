package com.ibtech.mall.database.entity;

import com.ibtech.mall.database.entity.enums.FeaturedCategory;
import com.ibtech.mall.database.entity.enums.Status;

public class Category implements IEntity {
    private long categoryId;
    private String categoryName;
    private Status status;
    private FeaturedCategory featuredCategory;

    public Category() {
    }

    public Category(long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(long categoryId, String categoryName, Status status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.status = status;
    }

    public Category(long categoryId, String categoryName, Status status, FeaturedCategory featuredCategory) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.status = status;
        this.featuredCategory = featuredCategory;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public FeaturedCategory getFeaturedCategory() {
        return featuredCategory;
    }

    public void setFeaturedCategory(FeaturedCategory featuredCategory) {
        this.featuredCategory = featuredCategory;
    }
}
