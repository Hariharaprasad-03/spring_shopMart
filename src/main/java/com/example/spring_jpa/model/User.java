package com.example.spring_jpa.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name ="users" ,
        uniqueConstraints = {
                @UniqueConstraint(name = "users_email_unique", columnNames = "email")
        }
)

public class User {

    @Id
    @Column(name = "mobileNumber" , nullable = false)
    private String id ;

    @Column(name = "username")
    private String name ;

    @Column(name = "password")
    private String password ;

    @Column(name = "email" ,nullable = false)
    private String email ;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Cart cart ;


    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders ;


    public User() {
    }

    public User(String id, String name,String password ,String email, Cart cart) {
        this.id = id;
        this.name = name;
        this.password = password ;
        this.cart = cart ;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }




    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {

        if (cart != null){
            cart.setUser(this);
            this.cart = cart ;
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                ", pass=" + password+
                '}';
    }
}
