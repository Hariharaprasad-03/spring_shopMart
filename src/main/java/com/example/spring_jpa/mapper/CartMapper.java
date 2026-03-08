package com.example.spring_jpa.mapper;

import com.example.spring_jpa.dto.CartDTO;
import com.example.spring_jpa.dto.CartItemDto;
import com.example.spring_jpa.model.Cart;
import com.example.spring_jpa.model.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {
    public CartItemDto toCartItemDto(CartItem item) {
        return new CartItemDto(
                item.getProduct().getId(),
                item.getProduct().getProductName(),
                item.getQuantity(),
                item.getProduct().getDiscount(),
                item.getProduct().getPrice()

        );
    }

    public CartDTO toCartDto(Cart cart) {
        List<CartItemDto> items = cart.getItems().stream()
                .map(this::toCartItemDto)
                .toList();

        double total = cart.getTotalAmount();

        return new CartDTO(cart.getCartId(), items, total);
    }
}
