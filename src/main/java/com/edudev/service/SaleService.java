package com.edudev.service;

import com.edudev.dto.SaleDetailRequest;
import com.edudev.dto.SaleRequest;
import com.edudev.dto.SalesByDayResponse;
import com.edudev.dto.TopProductResponse;
import com.edudev.model.Customer;
import com.edudev.model.Product;
import com.edudev.model.Sale;
import com.edudev.model.SaleDetail;
import com.edudev.repository.CustomerRepository;
import com.edudev.repository.ProductRepository;
import com.edudev.repository.SaleDetailRepository;
import com.edudev.repository.SaleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static tools.jackson.databind.ext.javatime.util.DecimalUtils.toBigDecimal;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SaleDetailRepository saleDetailRepository;

    private final CustomerRepository customerRepository;

    @Transactional
    public Sale create(SaleRequest request) {

        BigDecimal total = BigDecimal.ZERO;
        List<SaleDetail> details = new ArrayList<>();

        for (SaleDetailRequest item : request.getItems()) {

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // REGLA: NO VENDER SIN STOCK
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + product.getName());
            }

            BigDecimal price = product.getPrice().setScale(2, RoundingMode.HALF_UP);
           // BigDecimal price = toBigDecimal(product.getPrice());
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            BigDecimal subtotal = price.multiply(quantity).setScale(2, RoundingMode.HALF_UP);

            // DESCUENTO DE STOCK
            product.setStock(product.getStock() - item.getQuantity());

            SaleDetail detail = SaleDetail.builder()
                    .product(product)
                    .quantity(item.getQuantity())
                    .price(product.getPrice())
                    .subtotal(subtotal)
                    .build();

            total = total.add(subtotal);
            details.add(detail);
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Sale sale = Sale.builder()
                .date(LocalDateTime.now())
                .total(total.setScale(2, RoundingMode.HALF_UP))
                .customer(customer)
                .build();

        // RELACIÓN
        details.forEach(d -> d.setSale(sale));
        sale.setDetails(details);

        return saleRepository.save(sale);
    }

    public List<Sale> getAll() {

        return saleRepository.findAll();
    }

    public List<SalesByDayResponse> getSalesByDay() {
        return saleRepository.getSalesByDay();
    }

    public  List<TopProductResponse> getTopProductResponses(){
        return saleDetailRepository.getTopProducts();
    }

    private BigDecimal toBigDecimal(Double value) {
        if (value == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }


}
