package com.example.spring_jpa.services;


import com.example.spring_jpa.dto.OrderDto;
import com.example.spring_jpa.exception.InvalidRequestException;
import com.example.spring_jpa.exception.StackNotAvailableException;
import com.example.spring_jpa.mapper.OrderDtoMapper;
import com.example.spring_jpa.model.*;
import com.example.spring_jpa.repository.OrderRepository;
import com.example.spring_jpa.payload.CartCheckOutRequest;
import com.example.spring_jpa.util.IdGeneratorService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServices {

    @Autowired
    private  OrderRepository orderRepository ;
    @Autowired
    private  UserService userService ;
    @Autowired
    private  ProductService productService ;
    @Autowired
    private  IdGeneratorService idGenarator;
    @Autowired
    private  OrderDtoMapper mapper ;
    @Autowired
    private  EntityManager entityManager;
    @Autowired
    private  CartService cartService;




    @Transactional
    public OrderDto checkOut(CartCheckOutRequest request){

        User user = userService.getUserbyId(request.getUserId());


        List<CartItem> cartItems = user.getCart().getItems();
        List<OrderItem> preparedOrderItems = new ArrayList<>();
        double orderPrice = 0;


        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();
            String productId = product.getId();
            String productName = product.getProductName();
            int quantity = Math.max(cartItem.getQuantity(), 0);

            if (!productService.isProductExist(productId, productName)) {
                throw new InvalidRequestException("Product not found: " + productId);
            }

            int currentStock = productService.getStock(productId);
            System.out.println(currentStock);
            if (currentStock < quantity) {

                throw new StackNotAvailableException("Stock not available for " + productId);
            }


            double itemTotalPrice = product.getEfectivePrice() * quantity;

            OrderItem item = new OrderItem();
            item.setProductId(productId);
            item.setQuantity(quantity);
            item.setProductName(productName);
            item.setProductUnitPrice(product.getPrice());
            item.setDiscount(product.getDiscount());
            item.setTotalPrice(itemTotalPrice);

            orderPrice += itemTotalPrice;
            preparedOrderItems.add(item);
            System.out.println(preparedOrderItems);
        }

        if (preparedOrderItems.isEmpty() || orderPrice <= 0) {
            throw new InvalidRequestException("Cannot checkout an empty or invalid cart.");
        }



        for (OrderItem item : preparedOrderItems) {

            productService.decreaseStock(item.getProductId(), item.getQuantity());
        }


        Order order = Order.builder()
                .withId(idGenarator.generateId("ORD"))
                .withUser(user)
                .withUserName(user.getName())
                .withOrderAddress(request.getDelivaryAddress())
                .withPrice(orderPrice)
                .withOrderList(preparedOrderItems)
                .build();

        entityManager.persist(order);


        cartService.clearUserCart(user);
        return mapper.toDto(order);
    }

    public List<OrderDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderdtos = new ArrayList<>();

        for ( Order order : orders)
        {
            orderdtos.add(mapper.toDto(order));
        }
        return orderdtos;
    }

    public List<OrderDto> getAllOrdersOfUser(String id ){

        List<Order> orders = orderRepository.getAllOrderOfUser(id);

        List<OrderDto> orderDtos = new ArrayList<>();
        for( Order order : orders){
            orderDtos.add(mapper.toDto(order));
        }
        return orderDtos;
    }

    public List<OrderDto>getPendingOrders(){
        List<OrderDto> orderDtos  = new ArrayList<>();
        List<Order> orders = orderRepository.getPendingOrders();

        for ( Order order : orders){

            orderDtos.add(mapper.toDto(order));
        }
        return orderDtos;
    }

    public  int getOrdersCount(){

        return orderRepository.getCount();
    }

}
