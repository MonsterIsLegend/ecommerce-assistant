package com.jansolski.ecommerceassistant.controller;

import com.jansolski.ecommerceassistant.dto.CustomerDto;
import com.jansolski.ecommerceassistant.dto.CustomerAuthorizationDto;
import com.jansolski.ecommerceassistant.dto.LoginResponseDto;
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
    public ResponseEntity<CustomerDto> registerUser(@RequestBody @Valid CustomerAuthorizationDto dto) {
        CustomerDto created = authService.registerCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<CustomerDto> registerAdmin(@RequestBody @Valid CustomerAuthorizationDto dto) {
        CustomerDto created = authService.registerAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid CustomerAuthorizationDto customerAuthorizationDto) {
        LoginResponseDto login = authService.login(customerAuthorizationDto.getEmail(), customerAuthorizationDto.getPassword());
        return ResponseEntity.ok(login);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetDto dto) {
        authService.resetPassword(dto);
        return ResponseEntity.ok("Reset hasła przebiegł pomyślnie");
    }
}
