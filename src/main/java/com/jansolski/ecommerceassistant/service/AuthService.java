package com.jansolski.ecommerceassistant.service;

import com.jansolski.ecommerceassistant.dto.CustomerDto;
import com.jansolski.ecommerceassistant.dto.CustomerRegistrationDto;
import com.jansolski.ecommerceassistant.dto.PasswordResetDto;
import com.jansolski.ecommerceassistant.entity.Customer;
import com.jansolski.ecommerceassistant.enums.Role;
import com.jansolski.ecommerceassistant.mapper.CustomerMapper;
import com.jansolski.ecommerceassistant.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    public CustomerDto registerCustomer(CustomerRegistrationDto dto) {
        if (customerRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }
        Customer customer = Customer.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.CUSTOMER)
                .build();
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDto(saved);
    }

    public CustomerDto registerAdmin(CustomerRegistrationDto dto) {
        if (customerRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }
        Customer admin = Customer.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name("Admin")
                .role(Role.ADMIN)
                .build();
        Customer saved = customerRepository.save(admin);
        return customerMapper.toDto(saved);
    }

    public boolean login(String email, String password) {
      Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono użytkownika"));

        return passwordEncoder.matches(password, customer.getPassword());
    }

    @Transactional
    public void resetPassword(PasswordResetDto dto) {
        Customer customer = customerRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User with email " + dto.getEmail() + " not found"));

        customer.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        customerRepository.save(customer);
    }
}
