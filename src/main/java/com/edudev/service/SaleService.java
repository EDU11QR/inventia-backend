package com.edudev.service;

import com.edudev.dto.SaleDetailRequest;
import com.edudev.dto.SaleRequest;
import com.edudev.model.Product;
import com.edudev.model.Sale;
import com.edudev.model.SaleDetail;
import com.edudev.repository.ProductRepository;
import com.edudev.repository.SaleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Sale create(SaleRequest request) {

        double total = 0;
        List<SaleDetail> details = new ArrayList<>();

        for (SaleDetailRequest item : request.getItems()) {

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // REGLA: NO VENDER SIN STOCK
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + product.getName());
            }

            double subtotal = product.getPrice() * item.getQuantity();

            // DESCUENTO DE STOCK
            product.setStock(product.getStock() - item.getQuantity());

            SaleDetail detail = SaleDetail.builder()
                    .product(product)
                    .quantity(item.getQuantity())
                    .price(product.getPrice())
                    .subtotal(subtotal)
                    .build();

            total += subtotal;
            details.add(detail);
        }

        Sale sale = Sale.builder()
                .date(LocalDateTime.now())
                .total(total)
                .build();

        // RELACIÓN
        details.forEach(d -> d.setSale(sale));
        sale.setDetails(details);

        return saleRepository.save(sale);
    }

}
