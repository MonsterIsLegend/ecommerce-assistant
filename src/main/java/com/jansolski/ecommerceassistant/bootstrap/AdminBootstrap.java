package com.jansolski.ecommerceassistant.bootstrap;

import com.jansolski.ecommerceassistant.entity.Customer;
import com.jansolski.ecommerceassistant.enums.Role;
import com.jansolski.ecommerceassistant.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminBootstrap {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        String adminEmail = "admin@example.com";

        boolean adminExists = customerRepository.findByEmail(adminEmail).isPresent();
        if (!adminExists) {
            Customer admin = Customer.builder()
                    .email(adminEmail)
                    .password(passwordEncoder.encode("StrongAdminPass123"))
                    .role(Role.ADMIN)
                    .name("Admin")
                    .build();

            customerRepository.save(admin);
            System.out.println("Admin user created: " + adminEmail);
        }
    }
}
