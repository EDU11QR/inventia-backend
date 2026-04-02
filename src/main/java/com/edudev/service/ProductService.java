package com.edudev.service;


import com.edudev.dto.ProductRequest;
import com.edudev.model.Product;
import com.edudev.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product create(ProductRequest request) {

        validate(request);

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .stockMinimum(request.getStockMinimum())
                .category(request.getCategory())
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(product);
    }

    public List<Product> getAll() {

        return repository.findAll();
    }

    public Product update(Long id, ProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        validate(request);

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setStockMinimum(request.getStockMinimum());
        product.setCategory(request.getCategory());

        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // REGLAS DE NEGOCIO
    private void validate(ProductRequest request) {

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new RuntimeException("Nombre obligatorio");
        }

        if (request.getPrice() == null) {
            throw new RuntimeException("Precio obligatorio");
        }

        if (request.getStock() < 0) {
            throw new RuntimeException("Stock no puede ser negativo");
        }

        if (request.getStockMinimum() < 0) {
            throw new RuntimeException("Stock mínimo inválido");
        }
    }

}
