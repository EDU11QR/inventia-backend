package com.edudev.fiscal.repository;

import com.edudev.fiscal.model.DocumentType;
import com.edudev.fiscal.model.FiscalDocument;
import com.edudev.fiscal.model.FiscalDocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FiscalDocumentRepository extends JpaRepository<FiscalDocument, Long> {
    Optional<FiscalDocument> findBySaleId(Long saleId);
    Optional<FiscalDocument> findByFullNumber(String fullNumber);

    // Agreamos metodos para buscar por tipo, buscar por estado y ordenar por fecha

    List<FiscalDocument> finAllByOrderByIssuedAtDesc();
    List<FiscalDocument> finByDocumentTypeOrderByIssuedAtDesc(DocumentType documentType);
    List<FiscalDocument> finByStatusOrderByIssuedAtDesc(FiscalDocumentStatus status);

    List<FiscalDocument> finByDocumentTypeAndStatusOrderByIssuedAtDesc(
            DocumentType documentType,
            FiscalDocumentStatus status
    );

}
