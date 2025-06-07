package com.jansolski.ecommerceassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO służące do rejestracji nowego użytkownika i logowania")
public class CustomerAuthorizationDto {

    @Email(message = "Niepoprawny adres email")
    @NotBlank(message = "Email jest wymagany")
    @Schema(description = "Adres email użytkownika", example = "jan.kowalski@example.com", required = true)
    private String email;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 8, message = "Hasło musi mieć minimum 8 znaków")
    @Schema(description = "Hasło użytkownika", example = "StrongPass123!", required = true)
    private String password;
}
