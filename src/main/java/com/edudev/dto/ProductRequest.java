package com.edudev.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer stockMinimum;
    private String category;

}
