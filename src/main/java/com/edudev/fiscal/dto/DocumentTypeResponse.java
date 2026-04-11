package com.edudev.fiscal.dto;

import com.edudev.fiscal.model.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DocumentTypeResponse {
    private String code;
    private String name;

    public static DocumentTypeResponse from(DocumentType documentType) {
        return new DocumentTypeResponse(
                documentType.name(),
                formatName(documentType.name())
        );
    }

    private static String formatName(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }
}
