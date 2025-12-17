package com.example.spring_jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name ="order_table")
public class Order {

    @Id
    @Column(name="order_id",
            nullable = false)
    private String orderId;

    @Column(name = "amount")
    private double  price ;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType ;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_staus")
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name="user_id_fk" ,
            referencedColumnName = "mobileNumber",
            foreignKey = @ForeignKey(name = "fk_order_user"))
    @JsonBackReference
    private User user;

    @Column (name = "user_name")
    private String userName ;

    @Column(name = "address")
    private String orderAddress ;

    @Column(name = "order_date")
    LocalDateTime orderDate;

    @OneToMany(mappedBy = "order" ,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    List<OrderItem> orderList = new ArrayList<>();

    public Order() {
    }

    private Order(Builder builder) {
        this.orderId = builder.id;
        this.user = builder.user;
        this.orderAddress = builder.orderAddress;
        this.price = builder.price;


        if (builder.orderList == null) {
            this.orderList = new ArrayList<>();
        }

        this.setOrderList(builder.orderList);


        this.status = builder.status != null ? builder.status : OrderStatus.PROCESSING;
        this.paymentStatus = builder.paymentStatus != null ? builder.paymentStatus : PaymentStatus.PENDING;
        this.paymentType = builder.paymentType != null ? builder.paymentType : PaymentType.CASHONDELIVERY;
        this.orderDate = builder.orderDate != null ? builder.orderDate : LocalDateTime.now();
        this.userName = builder.userName != null ? builder.userName : "";
    }

    public String getId() {
        return orderId;
    }

    public void setId(String id) {
        this.orderId = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderList() {
        return orderList;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setOrderList(List<OrderItem> orderList) {

        for (OrderItem item : orderList){
            item.setOrder(this);
        }
        this.orderList = new ArrayList<>(orderList);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private User user;
        private String id;
        private List<OrderItem> orderList;
        private double price;


        private String orderAddress;
        private OrderStatus status;
        private PaymentStatus paymentStatus;
        private PaymentType paymentType;
        private LocalDateTime orderDate = LocalDateTime.now();
        private OrderStatus orderStatus = OrderStatus.PROCESSING;
        private String userName;


        public Builder(String id, User user, List<OrderItem> orderList, double price) {
            this.id = id;
            this.user = user;
            this.orderList = orderList;
            this.price = price;
        }


        public Builder() {

        }


        public Builder withId(String id) {
            this.id = id;
            System.out.println(id);
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            System.out.println(this.user);
            return this;
        }

        public Builder withUserName(String userName){
            this.userName = userName;
            return this ;
        }

        public Builder withOrderList(List<OrderItem> orderList) {
            this.orderList = orderList;
            System.out.println(orderList);
            return this;
        }

        public Builder withPrice(double price) {
            this.price = price;
            System.out.println(this.price);
            return this;
        }

        public Builder withOrderAddress(String orderAddress) {
            this.orderAddress = orderAddress;
            System.out.println(this.orderAddress);
            return this;
        }

        public Builder withStatus(OrderStatus status) {
            this.status = status;
            System.out.println(this.status);
            return this;
        }
        public Builder withPaymentStatus(PaymentStatus status){
            this.paymentStatus = status;
            System.out.println(this.paymentStatus);
            return this ;
        }

        public Builder withPaymentType(PaymentType type){
            this.paymentType = type ;
            System.out.println(this.paymentStatus);
            return this ;
        }


        public Order build() {

            if (this.id == null || this.user == null || this.orderList == null || this.price <= 0) {
                throw new IllegalStateException("Order ID, User, OrderList, and Price must be set.");
            }
            return new Order(this);
        }
    }
}



