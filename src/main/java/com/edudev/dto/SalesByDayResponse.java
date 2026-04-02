package com.edudev.dto;

import java.math.BigDecimal;

public class SalesByDayResponse {

    private String day;
    private BigDecimal total;

    public SalesByDayResponse(String day, BigDecimal total) {
        this.day = day;
        this.total = total;
    }

    public String getDay() {
        return day;
    }

    public BigDecimal getTotal() {
        return total;
    }
}