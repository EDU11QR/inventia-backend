package com.edudev.service;

import com.edudev.dto.DashboardResponse;
import com.edudev.repository.ProductRepository;
import com.edudev.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public DashboardResponse getDashboard() {

        Double revenue = saleRepository.getTotalRevenue();
        Long totalSales = saleRepository.getTotalSales();
        Long todaySales = saleRepository.getTodaySales();
        Long lowStock = productRepository.countLowStockProducts();

        return DashboardResponse.builder()
                .totalRevenue(revenue != null ? revenue : 0.0)
                .totalSales(totalSales != null ? totalSales : 0)
                .todaySales(todaySales != null ? todaySales : 0)
                .lowStockProducts(lowStock != null ? lowStock : 0)
                .build();
    }

}
