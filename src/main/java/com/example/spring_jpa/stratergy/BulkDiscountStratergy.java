package com.example.spring_jpa.stratergy;

import org.springframework.stereotype.Component;

@Component
public class BulkDiscountStratergy implements DiscountStratergy{

    private double discountRate = 10.0;

    @Override
    public double applyDiscount(double amount) {
        double discountMultiplier = 1.0 - (discountRate / 100.0);
        return amount * discountMultiplier;
    }
}
