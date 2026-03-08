package com.example.spring_jpa.controllers;

import com.example.spring_jpa.dto.DailyOrderDTO;
import com.example.spring_jpa.dto.TopCustomerDTO;
import com.example.spring_jpa.dto.TopProductDTO;
import com.example.spring_jpa.services.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/top-products")
    public List<TopProductDTO> getTopProducts(
            @RequestParam(defaultValue = "10") int k) {

        return analyticsService.getTopProducts(k);
    }

    @GetMapping("/top-customers")
    public List<TopCustomerDTO> getTopCustomers(
            @RequestParam(defaultValue = "10") int k) {

        return analyticsService.getTopCustomers(k);
    }

    @GetMapping("/orders/daily")
    public List<DailyOrderDTO> getDailyOrders() {

        return analyticsService.getDailyOrders();
    }

}
