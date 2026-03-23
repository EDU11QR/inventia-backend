package com.edudev.controller;

import com.edudev.dto.SaleRequest;
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

}
