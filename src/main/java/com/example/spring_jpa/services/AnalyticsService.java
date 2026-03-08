package com.example.spring_jpa.services;

import com.example.spring_jpa.dto.DailyOrderDTO;
import com.example.spring_jpa.dto.TopCustomerDTO;
import com.example.spring_jpa.dto.TopProductDTO;
import com.example.spring_jpa.repository.OrderRepository;
import com.example.spring_jpa.repository.ProductRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public AnalyticsService(ProductRepository productRepository,
                            OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    // Top K Products
    public List<TopProductDTO> getTopProducts(int k) {

        Pageable pageable = PageRequest.of(0, k);

        return productRepository.findTopSellingProducts(pageable);
    }

    // Top K Customers
    public List<TopCustomerDTO> getTopCustomers(int k) {

        Pageable pageable = PageRequest.of(0, k);

        return orderRepository.findTopCustomers(pageable);
    }

    // Daily Order Count
    public List<DailyOrderDTO> getDailyOrders() {

        List<Object[]> results = orderRepository.findDailyOrders();

        return results.stream()
                .map(row -> new DailyOrderDTO(
                        row[0].toString(),
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
}