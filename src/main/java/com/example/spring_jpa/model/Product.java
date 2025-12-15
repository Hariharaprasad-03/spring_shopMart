package com.example.spring_jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {


    @Id
    @Column(name= "product_id")
    private String id ;

    @Column(name = "product_name")
    private String productName ;

    @Column(name = "product_category")
    private String productCategory ;

    @Column(name = "price")
    private double price ;

    @Column(name = "discount")
    private double discount ;

    @Column(name = "stack_added_Date")
    private LocalDateTime stockDate ;

    @Column(name = "stock")
    private int stock;

    public Product() {
    }

    public Product(String id,
                   String productName,
                   String productCategory,
                   double price,
                   double discount,
                   LocalDateTime stockDate,
                   int stock) {
        this.id = id;
        this.productName = productName;
        this.productCategory = productCategory;
        this.price = price;
        this.discount = discount;
        this.stockDate = stockDate;
        this.stock= stock;
    }

    private Product(ProductBuilder builder){
        this.id  = builder.productID;
        this.productName = builder.productName;
        this.productCategory = builder.productType;
        this.price = builder.price;
        this.stock = builder.stock;
        this.discount = builder.discount;
        this.stockDate = builder.setStockDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDateTime getStockDate() {
        return stockDate;
    }

    public void setStockDate(LocalDateTime stockDate) {
        this.stockDate = stockDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public static ProductBuilder builder(){
        return new ProductBuilder();
    }
    public double getEfectivePrice(){
        double multiplyFactor = 1.0 -(discount/100.0);
        return price * multiplyFactor;
    }

    public static class ProductBuilder{
        private  String productID;
        private String productType;
        private String productName;
        private int stock ;
        private double discount ;
        private double price ;
        private LocalDateTime setStockDate = LocalDateTime.now();

        public ProductBuilder productId(String productId){
            this.productID = productId;
            return this;
        }
        public ProductBuilder productName (String productName){
            this.productName = productName;
            return this;
        }
        public ProductBuilder productType(String productType){
            this.productType = productType;
            return this;
        }
        public ProductBuilder stock(int stock){
            this.stock = stock ;
            return this ;
        }
        public ProductBuilder discount(double discount){
            this.discount = discount;
            return this;
        }
        public ProductBuilder price(double price ){
            this.price = price;
            return this ;
        }
        public Product build(){
            if (this.productID == null || this.productName == null
            || this.price == 0.0 ) {
                throw new IllegalStateException("ID and Name are required fields.");
            }
            return new Product(this);
        }
    }
}
