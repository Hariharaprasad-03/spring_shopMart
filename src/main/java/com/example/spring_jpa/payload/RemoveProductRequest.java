package com.example.spring_jpa.payload;

public class RemoveProductRequest {

    private String productId ;

    public RemoveProductRequest(){

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
