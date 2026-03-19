package com.edudev.service;

import com.edudev.dto.AlertResponse;
import com.edudev.model.Product;
import com.edudev.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final ProductRepository repository;

    public List<AlertResponse> getLowStockAlerts() {

        List<Product> products = repository.findLowStockProducts();

        return products.stream()
                .map(p -> AlertResponse.builder()
                        .productId(p.getId())
                        .productName(p.getName())
                        .stock(p.getStock())
                        .stockMinimum(p.getStockMinimum())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
