package com.edudev.service;

import com.edudev.dto.CustomerRequest;
import com.edudev.dto.CustomerResponse;
import com.edudev.model.Customer;
import com.edudev.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse create(CustomerRequest request){
        validate(request);
        customerRepository.findByDocumentNumber(request.getDocumentNumber())
                .ifPresent(customer -> {
                    throw new RuntimeException("Ya existe un cliente con ese número de documento");
                });

        Customer customer = Customer.builder()
                .name(request.getName())
                .documentNumber(request.getDocumentNumber())
                .documentType(request.getDocumentType())
                .address(request.getAddress())
                .email(request.getEmail())
                .createdAt(LocalDateTime.now())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return mapToResponse(savedCustomer);
    }

    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return mapToResponse(customer);
    }

    private void validate(CustomerRequest request) {
        if (isBlank(request.getName())) {
            throw new RuntimeException("El nombre del cliente es obligatorio");
        }

        if (isBlank(request.getDocumentType())) {
            throw new RuntimeException("El tipo de documento es obligatorio");
        }

        if (isBlank(request.getDocumentNumber())) {
            throw new RuntimeException("El número de documento es obligatorio");
        }
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .documentNumber(customer.getDocumentNumber())
                .documentType(customer.getDocumentType())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .build();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

}


