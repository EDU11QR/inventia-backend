package com.edudev.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private Double totalRevenue;
    private Long totalSales;
    private Long todaySales;
    private Long lowStockProducts;

}
