package com.example.spring_jpa.mapper;

import com.example.spring_jpa.model.Order;
import com.example.spring_jpa.model.OrderItem;
import com.example.spring_jpa.dto.OrderDto;
import com.example.spring_jpa.dto.OrderItemDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoMapper {

    private List<OrderItemDto> toOrderItemDtoList(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::toOrderItemDto)
                .collect(Collectors.toList());
    }


    public OrderItemDto toOrderItemDto(OrderItem item) {
        if (item == null) {
            return null;
        }
        return new OrderItemDto(
                item.getProductId(),
                item.getProductName(),
                item.getProductUnitPrice(),
                item.getQuantity(),
                item.getTotalPrice(),
                item.getDiscount()
        );
    }


    public OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }


        List<OrderItemDto> itemDtos = toOrderItemDtoList(order.getOrderList());


        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getUser().getName(),
                order.getPrice(),
                order.getStatus(),
                order.getPaymentType(),
                order.getPaymentStatus(),
                order.getOrderAddress(),
                order.getOrderDate(),
                itemDtos
        );
    }
}