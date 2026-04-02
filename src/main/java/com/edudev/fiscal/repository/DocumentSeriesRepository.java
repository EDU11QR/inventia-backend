package com.edudev.fiscal.repository;

import com.edudev.fiscal.model.DocumentSeries;
import com.edudev.fiscal.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentSeriesRepository extends JpaRepository<DocumentSeries,Long> {
    Optional<DocumentSeries> findByDocumentTypeAndActiveTrue(DocumentType documentType);
}
