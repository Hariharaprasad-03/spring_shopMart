package com.example.spring_jpa.dto;

public class TopProductDTO {

    private String productId;
    private String productName;
    private Long totalSold;

    public TopProductDTO(String productId, String productName, Long totalSold) {
        this.productId = productId;
        this.productName = productName;
        this.totalSold = totalSold;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getTotalSold() {
        return totalSold;
    }
}