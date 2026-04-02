package com.edudev.fiscal.dto;

import com.edudev.fiscal.model.DocumentType;
import com.edudev.fiscal.model.FiscalDocumentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiscalDocumentResponse {

    private Long id;
    private Long saleId;

    private DocumentType documentType;
    private String series;
    private Long number;
    private String fullNumber;

    private FiscalDocumentStatus status;

    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal total;

    private String customerDocumentType;
    private String customerDocumentNumber;
    private String customerName;

    private LocalDateTime issuedAt;

}
