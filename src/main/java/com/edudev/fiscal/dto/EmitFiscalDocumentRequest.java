package com.edudev.fiscal.dto;

import com.edudev.fiscal.model.DocumentType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmitFiscalDocumentRequest {

    // Atributos
    private Long saleId;
    private DocumentType documentType;

    private String customerDocumentType;
    private String customerDocumentNumber;
    private String customerName;

}
