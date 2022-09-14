package com.ibtech.mall.database.entity;

public class Order extends Product implements IEntity {
    private long orderId;
    private int quantity;
    private User user;

    public Order() {
    }

    public Order(long orderId, int quantity) {
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
