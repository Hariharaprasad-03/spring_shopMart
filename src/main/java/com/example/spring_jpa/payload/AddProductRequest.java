package com.example.spring_jpa.payload;

public class AddProductRequest {

    private String productName ;
    private String productType ;
    private Double price ;
    private  Integer stock ;
    private Double discount ;

    public AddProductRequest(String productName, String productType, double price, int stock, double discount) {
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
    }
    public AddProductRequest(){

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
