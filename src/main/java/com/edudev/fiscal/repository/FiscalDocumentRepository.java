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

    List<FiscalDocument> findAllByOrderByIssuedAtDesc();
    List<FiscalDocument> findByDocumentTypeOrderByIssuedAtDesc(DocumentType documentType);
    List<FiscalDocument> findByStatusOrderByIssuedAtDesc(FiscalDocumentStatus status);

    List<FiscalDocument> findByDocumentTypeAndStatusOrderByIssuedAtDesc(
            DocumentType documentType,
            FiscalDocumentStatus status
    );

}
