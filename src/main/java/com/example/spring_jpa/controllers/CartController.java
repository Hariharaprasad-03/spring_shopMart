package com.example.spring_jpa.controllers;

import com.example.spring_jpa.dto.CartDto;
import com.example.spring_jpa.dto.OrderDto;
import com.example.spring_jpa.payload.AddCartItemRequest;
import com.example.spring_jpa.payload.CartCheckOutRequest;
import com.example.spring_jpa.payload.RemoveCartItemRequest;
import com.example.spring_jpa.services.CartService;
import com.example.spring_jpa.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService ;
    @Autowired
    private OrderServices orderServices ;


    @PostMapping("/addItem")
    public ResponseEntity<?> addToCart(@RequestBody AddCartItemRequest request){

        try {
            CartDto added = cartService.addCartItem(request);
            return ResponseEntity.status(200).body(added);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<?> removeCartItem(@RequestBody RemoveCartItemRequest request) {

        try {
            CartDto dto = cartService.removeItemFromCart(request);
            return ResponseEntity.status(200).body(dto);
        }
        catch ( Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/checkOut")
    public ResponseEntity<?> checkOut(@RequestBody CartCheckOutRequest request){
        try {
            OrderDto order = orderServices.checkOut(request);
            return ResponseEntity.status(200).body(order);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
