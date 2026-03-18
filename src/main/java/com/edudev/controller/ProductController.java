package com.edudev.controller;


import com.edudev.dto.ProductRequest;
import com.edudev.dto.ProductResponse;
import com.edudev.model.Product;
import com.edudev.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request) {

        Product product = service.create(request);

        return map(product);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id,
                                  @RequestBody ProductRequest request) {

        Product product = service.update(id, request);

        return map(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private ProductResponse map(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
    }

}
