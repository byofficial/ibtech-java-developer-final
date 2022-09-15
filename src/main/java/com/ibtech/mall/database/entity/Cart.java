package com.ibtech.mall.database.entity;

public class Cart extends Product implements IEntity {
    private long cartId;
    private long quantity;

    public Cart() {

    }

    public Cart(long cartId, long productId, String getProductName, String imagePath, double salesPrice, long quantity) {
        super(productId, getProductName, imagePath, salesPrice);
        this.cartId = cartId;
        this.quantity = quantity;
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
