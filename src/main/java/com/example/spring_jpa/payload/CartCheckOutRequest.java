package com.example.spring_jpa.payload;


public class CartCheckOutRequest {

    private String userId ;
    private String cartId ;
    private String delivaryAddress ;
    public String paymentType ;

    public CartCheckOutRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getDelivaryAddress() {
        return delivaryAddress;
    }

    public void setDelivaryAddress(String delivaryAddress) {
        this.delivaryAddress = delivaryAddress;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
