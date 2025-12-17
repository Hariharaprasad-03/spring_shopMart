package com.example.spring_jpa.model;

import jakarta.persistence.*;

@Entity
@Table( name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id ;

    @Column(name = "product_name")
    private  String productName ;

    @Column(name = "product_id")
    private String productId ;

    @Column(name = "product_unit_price")
    private double productUnitPrice ;

    @Column(name = "quantity")
    private int quantity ;



    @Column(name = "totalPrice")
    private double totalPrice ;

    @Column(name = "discount_rate")
    private double discount ;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(
            name ="order_id",
            referencedColumnName = "order_id" ,
            foreignKey = @ForeignKey(name = "order_id_fk"))
    private Order order ;

    public OrderItem() {
    }

    public OrderItem(long id, String productName, String productId, int quantity, double discount, double totalPrice, Order order) {
        this.id = id;
        this.productName = productName;
        this.productId = productId;
        this.quantity = quantity;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }
}
