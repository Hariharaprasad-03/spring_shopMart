package com.example.spring_jpa.controllers;


import com.example.spring_jpa.dto.OrderDto;
import com.example.spring_jpa.services.OrderServices;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private  OrderServices orderServices;

    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllOrders(){
        List<OrderDto> lists = orderServices.getAllOrders();
        return ResponseEntity.status(200).body(lists);
    }

    @GetMapping("api/orders/{id}")
    public ResponseEntity getAllOrdersOfUser(@PathVariable("id") String id){

        try {
            List<OrderDto> orders = orderServices.getAllOrdersOfUser(id);
            return ResponseEntity.status(200).body(orders);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }



}
