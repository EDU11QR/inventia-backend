package com.edudev.repository;

import com.edudev.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(s.total) FROM Sale s")
    Double getTotalRevenue();

    @Query("SELECT COUNT(s) FROM Sale s")
    Long getTotalSales();

    @Query("SELECT COUNT(s) FROM Sale s WHERE DATE(s.date) = CURRENT_DATE")
    Long getTodaySales();

}
