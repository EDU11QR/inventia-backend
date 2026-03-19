package com.edudev.repository;

import com.edudev.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStockLessThanEqual(Integer stock);

    @Query("SELECT p FROM Product p WHERE p.stock <= p.stockMinimum")
    List<Product> findLowStockProducts();
}
