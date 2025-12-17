package com.example.spring_jpa.payload;

public class AddCartItemRequest {
    private String cartId;
    private String productId;
    private int quantity;
    private String userId;

    // optional if you derive cart from auth/session


    public AddCartItemRequest(String cartId, String productId, int quantity, String userId) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.userId = userId;
    }

    public String getCartId() {
        return this.cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
