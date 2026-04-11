package com.edudev.fiscal.controller;

import com.edudev.fiscal.dto.DocumentTypeResponse;
import com.edudev.fiscal.model.DocumentType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/document-types")
@CrossOrigin(origins = "http://localhost:5173")
public class DocumentTypeController {

    @GetMapping
    public ResponseEntity<List<DocumentTypeResponse>> findAll() {
        List<DocumentTypeResponse> response = Arrays.stream(DocumentType.values())
                .map(DocumentTypeResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }
}