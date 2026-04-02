package com.edudev.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 12, scale = 2)
    private BigDecimal total;

    private LocalDateTime date;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SaleDetail> details;

}
