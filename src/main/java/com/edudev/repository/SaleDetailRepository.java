package com.edudev.repository;

import com.edudev.dto.TopProductResponse;
import com.edudev.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

    @Query("""
        SELECT new com.edudev.dto.TopProductResponse(
            sd.product.name,
            SUM(sd.quantity)
        )
        FROM SaleDetail sd
        GROUP BY sd.product.name
        ORDER BY SUM(sd.quantity) DESC
    """)
    List<TopProductResponse> getTopProducts();

}
