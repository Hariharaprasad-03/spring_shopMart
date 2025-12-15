package com.example.spring_jpa.dto;

import java.io.Serializable;

public record OrderItemDto(
        String productId,
        String productName,
        int quantity,
        double totalPrice,
        double discount
) implements Serializable {
    // Records automatically generate constructor, accessors (e.g., productId()), equals/hashCode, and toString.
}