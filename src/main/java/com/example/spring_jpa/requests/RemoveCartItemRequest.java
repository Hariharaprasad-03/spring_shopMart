package com.example.spring_jpa.requests;

public class RemoveCartItemRequest {

    private String cartId ;
    private long cartItemId ;
    private String ProductId ;
    private String ProductName ;
    private int quantity ;

    public RemoveCartItemRequest(String cartId, String productId, String productName, int quantity) {
        this.cartId = cartId;
        ProductId = productId;
        ProductName = productName;
        this.quantity = quantity;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
    }
    public RemoveCartItemRequest(){

    }
}
