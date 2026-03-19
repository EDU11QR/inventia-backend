package com.edudev.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertResponse {

    private Long productId;
    private String productName;
    private Integer stock;
    private Integer stockMinimum;

}
