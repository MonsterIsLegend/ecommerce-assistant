package com.jansolski.ecommerceassistant.controller;

import com.jansolski.ecommerceassistant.dto.AddressDto;
import com.jansolski.ecommerceassistant.dto.CustomerDto;
import com.jansolski.ecommerceassistant.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerDto dto) {
        CustomerDto created = customerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<CustomerDto> addAddress(
            @PathVariable Long customerId,
            @RequestBody @Valid AddressDto dto) {
        return ResponseEntity.status(201).body(customerService.addAddress(customerId, dto));
    }

    @PutMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<CustomerDto> updateAddress(
            @PathVariable Long customerId,
            @PathVariable Long addressId,
            @RequestBody @Valid AddressDto dto) {
        return ResponseEntity.ok(customerService.updateAddress(customerId, addressId, dto));
    }

    @DeleteMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<CustomerDto> deleteAddress(
            @PathVariable Long customerId,
            @PathVariable Long addressId) {
        return ResponseEntity.ok(customerService.deleteAddress(customerId, addressId));
    }

}
