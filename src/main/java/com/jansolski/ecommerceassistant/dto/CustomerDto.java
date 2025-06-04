package com.jansolski.ecommerceassistant.dto;

import com.jansolski.ecommerceassistant.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO reprezentujący użytkownika systemu")
public class CustomerDto {

    @Schema(description = "Unikalny identyfikator użytkownika", example = "1")
    private Long id;

    @Schema(description = "Email użytkownika", example = "jan.kowalski@example.com")
    private String email;

    @Schema(description = "Imię i nazwisko użytkownika", example = "Jan Kowalski")
    private String name;

    @Schema(description = "Rola użytkownika w systemie", example = "CUSTOMER")
    private Role role;

    @Schema(description = "Lista adresów użytkownika")
    private List<AddressDto> addresses;
}
