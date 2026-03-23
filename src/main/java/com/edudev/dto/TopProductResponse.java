package com.edudev.dto;

public class TopProductResponse {

    private String productName;
    private Long totalQuantity;

    public TopProductResponse(String productName, Long totalQuantity){
        this.productName = productName;
        this.totalQuantity = totalQuantity;
    }

    public String getProductName(){
        return productName;
    }
    public Long getTotalQuantity(){
        return  totalQuantity;
    }
}
