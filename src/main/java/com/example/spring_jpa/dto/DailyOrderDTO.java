package com.example.spring_jpa.dto;

public class DailyOrderDTO {

    private String date;
    private Long totalOrders;

    public DailyOrderDTO(String date, Long totalOrders) {
        this.date = date;
        this.totalOrders = totalOrders;
    }

}