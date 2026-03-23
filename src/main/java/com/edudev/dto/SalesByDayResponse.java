package com.edudev.dto;

public class SalesByDayResponse {

    private String day;
    private Double total;

    public SalesByDayResponse(String day, Double total) {
        this.day = day;
        this.total = total;
    }

    public String getDay() {
        return day;
    }

    public Double getTotal() {
        return total;
    }
}