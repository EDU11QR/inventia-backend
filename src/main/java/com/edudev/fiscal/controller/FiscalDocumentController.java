package com.edudev.fiscal.controller;

import com.edudev.fiscal.dto.EmitFiscalDocumentRequest;
import com.edudev.fiscal.dto.FiscalDocumentResponse;
import com.edudev.fiscal.model.DocumentType;
import com.edudev.fiscal.model.FiscalDocumentStatus;
import com.edudev.fiscal.service.FiscalDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fiscal-documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class FiscalDocumentController {

    private final FiscalDocumentService fiscalDocumentService;

    @PostMapping("/emit")
    public ResponseEntity<FiscalDocumentResponse> emitDocument(
            @RequestBody EmitFiscalDocumentRequest request
    ) {
        FiscalDocumentResponse response = fiscalDocumentService.emitDocument(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/sale/{saleId}")
    public ResponseEntity<FiscalDocumentResponse> findBySaleId(@PathVariable Long saleId) {
        FiscalDocumentResponse response = fiscalDocumentService.findBySaleId(saleId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<FiscalDocumentResponse> finById(@PathVariable Long id){
        FiscalDocumentResponse response = fiscalDocumentService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FiscalDocumentResponse>> findAll(
            @RequestParam(required = false)DocumentType documentType,
            @RequestParam(required = false)FiscalDocumentStatus status
            ){
        List<FiscalDocumentResponse> response = fiscalDocumentService.findAll(documentType,status);

        return  ResponseEntity.ok(response);
    }


}// Final de la clase
