package com.ibtech.mall.database.entity;

public class Cart extends Product implements IEntity {
    private long cartId;
    private int quantity;

    public Cart() {
    }

    public Cart(long cartId, int quantity) {
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
