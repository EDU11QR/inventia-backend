package com.edudev.fiscal.service;

import com.edudev.fiscal.dto.EmitFiscalDocumentRequest;
import com.edudev.fiscal.dto.FiscalDocumentResponse;
import com.edudev.fiscal.model.DocumentSeries;
import com.edudev.fiscal.model.DocumentType;
import com.edudev.fiscal.model.FiscalDocument;
import com.edudev.fiscal.model.FiscalDocumentStatus;
import com.edudev.fiscal.repository.DocumentSeriesRepository;
import com.edudev.fiscal.repository.FiscalDocumentRepository;
import com.edudev.model.Customer;
import com.edudev.model.Sale;
import com.edudev.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FiscalDocumentService {

    private static final BigDecimal IGV_RATE = new BigDecimal("0.18");

    private final FiscalDocumentRepository fiscalDocumentRepository;
    private final DocumentSeriesRepository documentSeriesRepository;
    private final SaleRepository saleRepository;

    @Transactional
    public FiscalDocumentResponse emitDocument(EmitFiscalDocumentRequest request) {
        validateRequest(request);

        Sale sale = saleRepository.findById(request.getSaleId())
                .orElseThrow(() -> new RuntimeException("La venta no existe"));

        if (sale.getCustomer() == null) {
            throw new RuntimeException("La venta no tiene un cliente asociado");
        }

        Customer customer = sale.getCustomer();

        validateCustomerForDocument(customer, request.getDocumentType());

        fiscalDocumentRepository.findBySaleId(sale.getId())
                .ifPresent(doc -> {
                    throw new RuntimeException("La venta ya tiene un comprobante fiscal emitido");
                });

        DocumentSeries seriesConfig = documentSeriesRepository
                .findByDocumentTypeAndActiveTrue(request.getDocumentType())
                .orElseThrow(() -> new RuntimeException("No existe una serie activa para el tipo de documento"));

        Long nextNumber = seriesConfig.getCurrentNumber() + 1;
        String fullNumber = seriesConfig.getSeries() + "-" + String.format("%08d", nextNumber);

        BigDecimal total = sale.getTotal().setScale(2, RoundingMode.HALF_UP);
        BigDecimal subtotal = total.divide(
                BigDecimal.ONE.add(IGV_RATE),
                2,
                RoundingMode.HALF_UP
        );
        BigDecimal igv = total.subtract(subtotal).setScale(2, RoundingMode.HALF_UP);

        FiscalDocument fiscalDocument = FiscalDocument.builder()
                .documentType(request.getDocumentType())
                .series(seriesConfig.getSeries())
                .number(nextNumber)
                .fullNumber(fullNumber)
                .status(FiscalDocumentStatus.GENERADO)
                .subtotal(subtotal)
                .igv(igv)
                .total(total)
                .customerDocumentType(customer.getDocumentType())
                .customerDocumentNumber(customer.getDocumentNumber())
                .customerName(customer.getName())
                .issuedAt(LocalDateTime.now())
                .sale(sale)
                .build();

        FiscalDocument savedDocument = fiscalDocumentRepository.save(fiscalDocument);

        seriesConfig.setCurrentNumber(nextNumber);
        documentSeriesRepository.save(seriesConfig);

        return mapToResponse(savedDocument);
    }

    public FiscalDocumentResponse findBySaleId(Long saleId) {
        FiscalDocument document = fiscalDocumentRepository.findBySaleId(saleId)
                .orElseThrow(() -> new RuntimeException("No existe comprobante para esta venta"));

        return mapToResponse(document);
    }

    public FiscalDocumentResponse findById(Long id) {
        FiscalDocument document = fiscalDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe comprobante con ese id"));

        return mapToResponse(document);
    }

    public List<FiscalDocumentResponse> findAll(DocumentType documentType, FiscalDocumentStatus status) {
        List<FiscalDocument> documents;

        if (documentType != null && status != null) {
            documents = fiscalDocumentRepository
                    .findByDocumentTypeAndStatusOrderByIssuedAtDesc(documentType, status);
        } else if (documentType != null) {
            documents = fiscalDocumentRepository
                    .findByDocumentTypeOrderByIssuedAtDesc(documentType);
        } else if (status != null) {
            documents = fiscalDocumentRepository
                    .findByStatusOrderByIssuedAtDesc(status);
        } else {
            documents = fiscalDocumentRepository.findAllByOrderByIssuedAtDesc();
        }

        return documents.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private void validateRequest(EmitFiscalDocumentRequest request) {
        if (request.getSaleId() == null) {
            throw new RuntimeException("El saleId es obligatorio");
        }

        if (request.getDocumentType() == null) {
            throw new RuntimeException("El tipo de documento es obligatorio");
        }
    }

    private void validateCustomerForDocument(Customer customer, DocumentType documentType) {
        if (isBlank(customer.getName())) {
            throw new RuntimeException("El cliente asociado a la venta no tiene nombre");
        }

        if (documentType == DocumentType.FACTURA) {
            if (isBlank(customer.getDocumentType())
                    || isBlank(customer.getDocumentNumber())) {
                throw new RuntimeException("Para FACTURA el cliente debe tener tipo y número de documento");
            }
        }

        if (documentType == DocumentType.BOLETA || documentType == DocumentType.NOTA_VENTA) {
            if (isBlank(customer.getName())) {
                throw new RuntimeException("Para este comprobante el cliente debe tener nombre");
            }
        }
    }

    private FiscalDocumentResponse mapToResponse(FiscalDocument document) {
        return FiscalDocumentResponse.builder()
                .id(document.getId())
                .saleId(document.getSale().getId())
                .documentType(document.getDocumentType())
                .series(document.getSeries())
                .number(document.getNumber())
                .fullNumber(document.getFullNumber())
                .status(document.getStatus())
                .subtotal(document.getSubtotal())
                .igv(document.getIgv())
                .total(document.getTotal())
                .customerDocumentType(document.getCustomerDocumentType())
                .customerDocumentNumber(document.getCustomerDocumentNumber())
                .customerName(document.getCustomerName())
                .issuedAt(document.getIssuedAt())
                .build();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}