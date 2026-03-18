package com.edudev.controller;

import com.edudev.dto.SaleRequest;
import com.edudev.model.Sale;
import com.edudev.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @PostMapping
    public Sale create(@RequestBody SaleRequest request) {
        return service.create(request);
    }

}
