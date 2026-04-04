package com.edudev.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaleRequest {

    private List<SaleDetailRequest> items;

    // se permite asociar a un cliente en la solicitud de venta
    private Long customerId;

}
