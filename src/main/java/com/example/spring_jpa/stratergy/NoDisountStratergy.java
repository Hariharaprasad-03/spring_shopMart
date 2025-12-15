package com.example.spring_jpa.stratergy;

import org.springframework.stereotype.Component;

@Component
public class NoDisountStratergy  implements  DiscountStratergy{

    @Override
    public double applyDiscount(double amount) {
        return amount;
    }
}
