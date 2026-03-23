package com.edudev.controller;

import com.edudev.dto.SaleRequest;
import com.edudev.dto.SalesByDayResponse;
import com.edudev.dto.TopProductResponse;
import com.edudev.model.Sale;
import com.edudev.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @PostMapping
    public Sale create(@RequestBody SaleRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<Sale> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-day")
    public List<SalesByDayResponse> getSalesByDay() {
        return service.getSalesByDay();
    }

    @GetMapping("/top-products")
    public List<TopProductResponse> getTopProductResponses(){
        return service.getTopProductResponses();
    }

}
