package com.edudev.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaleRequest {

    private List<SaleDetailRequest> items;

}
