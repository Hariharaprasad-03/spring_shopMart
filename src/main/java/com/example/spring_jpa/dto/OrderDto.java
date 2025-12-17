package com.example.spring_jpa.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.example.spring_jpa.model.OrderStatus;
import com.example.spring_jpa.model.PaymentStatus;
import com.example.spring_jpa.model.PaymentType;


public record OrderDto(
        String id,
        String userId,
        String userName ,
        double price,
        OrderStatus status,
        PaymentType paymentType,
        PaymentStatus paymentStatus,
        String orderAddress,
        LocalDateTime orderDate,
        List<OrderItemDto> items // Crucial: This links to the separate OrderItemDto
) {

}