package com.edudev.fiscal.model;

import com.edudev.model.Sale;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fiscal_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiscalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Column(nullable = false, length = 10)
    private String series;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false, length = 20, unique = true)
    private String fullNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FiscalDocumentStatus status;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal igv;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(length = 20)
    private String customerDocumentType;

    @Column(length = 20)
    private String customerDocumentNumber;

    @Column(length = 255)
    private String customerName;

    @Column(length = 500)
    private String xmlPath;

    @Column(length = 500)
    private String cdrPath;

    @Column(length = 1000)
    private String sunatMessage;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @OneToOne
    @JoinColumn(name = "sale_id", nullable = false, unique = true)
    private Sale sale;
}