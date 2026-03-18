package com.edudev.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Integer stockMinimum;
    private String category;

}
