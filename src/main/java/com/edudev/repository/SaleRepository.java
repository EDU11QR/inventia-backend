package com.edudev.repository;

import com.edudev.dto.SalesByDayResponse;
import com.edudev.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(s.total) FROM Sale s")
    Double getTotalRevenue();

    @Query("SELECT COUNT(s) FROM Sale s")
    Long getTotalSales();

    @Query("SELECT COUNT(s) FROM Sale s WHERE DATE(s.date) = CURRENT_DATE")
    Long getTodaySales();

    @Query("""
    SELECT new com.edudev.dto.SalesByDayResponse(
        CAST(s.date as string),
        SUM(s.total)
    )
    FROM Sale s
    GROUP BY CAST(s.date as string)
    ORDER BY CAST(s.date as string)
    """)
    List<SalesByDayResponse> getSalesByDay();

}
