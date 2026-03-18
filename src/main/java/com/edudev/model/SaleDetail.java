package com.edudev.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sale_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double price;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
