package com.example.spring_jpa.dto;

import java.util.List;

public class CartDTO {

    private String cartId ;
    private String userId ;
    private List<CartItemDto> cartItems ;
    private double totalAmount ;

   public CartDTO(String cartId , List<CartItemDto> items , double total){
       this.cartId = cartId;
       this.cartItems = items ;
       this.totalAmount=total;
   }
}
