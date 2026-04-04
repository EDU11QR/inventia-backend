package com.edudev.controller;

import com.edudev.dto.CustomerRequest;
import com.edudev.dto.CustomerResponse;
import com.edudev.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private final CustomerService customerService;

    // Crear Cliente
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request){
        CustomerResponse response = customerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Listar Clientes
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    // Buscar por Cliente por id
    @GetMapping("/id")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getById(id));
    }
}
