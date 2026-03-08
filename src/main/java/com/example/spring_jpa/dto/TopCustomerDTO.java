package com.example.spring_jpa.dto;

public class TopCustomerDTO {

    private String userId;
    private String userName;
    private Long orderCount;

    public TopCustomerDTO(String userId, String userName, Long orderCount) {
        this.userId = userId;
        this.userName = userName;
        this.orderCount = orderCount;
    }

}