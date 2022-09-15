package com.ibtech.mall.database.entity;

public class Orders extends Product implements IEntity {
    private long orderId;
    private long quantity;
    private long accountId;

    public Orders() {
    }

    public Orders(long quantity, long accountId) {

        this.quantity = quantity;
        this.accountId = accountId;
    }

    public Orders(long orderId, long quantity, long accountId) {

        this.orderId = orderId;
        this.quantity = quantity;
        this.accountId = accountId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
