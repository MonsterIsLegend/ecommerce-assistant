package com.jansolski.ecommerceassistant.controller;

import com.jansolski.ecommerceassistant.dto.CustomerDto;
import com.jansolski.ecommerceassistant.dto.CustomerRegistrationDto;
import com.jansolski.ecommerceassistant.dto.PasswordResetDto;
import com.jansolski.ecommerceassistant.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerUser(@RequestBody @Valid CustomerRegistrationDto dto) {
        CustomerDto created = authService.registerCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<CustomerDto> registerAdmin(@RequestBody @Valid CustomerRegistrationDto dto) {
        CustomerDto created = authService.registerAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        boolean success = authService.login(email, password);
        return success ? ResponseEntity.ok("Zalogowano pomyślnie") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Błędne dane logowania");
    }
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetDto dto) {
        authService.resetPassword(dto);
        return ResponseEntity.ok("Reset hasła przebiegł pomyślnie");
    }
}
