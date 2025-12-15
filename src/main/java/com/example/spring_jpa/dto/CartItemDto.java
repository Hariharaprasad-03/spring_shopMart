package com.example.spring_jpa.dto;

public class CartItemDto {
    private String productId;
    private String productName;

    private int quantity;
    private double discount ;
    private double finalPrice ;

    public CartItemDto(String productId  ,
                String productName ,
                int quantity ,
                double discount ,
                double finalPrice
                ){
        this.discount = discount;
        this.productId = productId;
        this.finalPrice = finalPrice;
        this.productName = productName;
        this.quantity = quantity;
    }
}
