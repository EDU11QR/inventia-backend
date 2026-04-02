package com.edudev.fiscal.repository;

import com.edudev.fiscal.model.FiscalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiscalDocumentRepository extends JpaRepository<FiscalDocument, Long> {
    Optional<FiscalDocument> findBySaleId(Long saleId);
    Optional<FiscalDocument> findByFullNumber(String fullNumber);
}
