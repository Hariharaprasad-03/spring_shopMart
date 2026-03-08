package com.example.spring_jpa.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "cartId"
)
@Entity
public class Cart {
    @Id
    private String cartId ;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItems  = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id " , referencedColumnName = "mobileNumber",
    foreignKey = @ForeignKey(name = "cart_user_fk"))
    private User user ;

    public Cart() {
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    public User getUser(){
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;

    }


    public List<CartItem> getItems() {
        return cartItems;
    }

    public void setItems(List<CartItem> items) {
        this.cartItems = items;
    }
    public void addItem(CartItem item) {

        if (item == null) return;
        item.setCart(this);
        cartItems.add(item);
    }

    public void removeItem(CartItem item) {
        item.setCart(null);
        cartItems.remove(item);
    }

    public double getTotalAmount(){
        double amount = cartItems.stream()
                .mapToDouble(item -> item.getTotalPrice())
                .sum();
        return amount;
    }
}
