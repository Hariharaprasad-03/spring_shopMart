package com.example.spring_jpa.stratergy;

import org.springframework.stereotype.Component;

@Component
public class WeekendDiscountStratergy implements  DiscountStratergy{

    private double disCountrate = 5;

    @Override
    public double applyDiscount(double amount) {

        double discountMultiplier = 1.0 - (disCountrate/100.0);
        return amount * discountMultiplier ;
    }
}
