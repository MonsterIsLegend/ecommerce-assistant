package com.jansolski.ecommerceassistant.dto;

import com.jansolski.ecommerceassistant.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO będące obiektem zwrotnym przy logowaniu")
public class LoginResponseDto {
    private Long id;
    private String email;
    private Role role;
    private String message;
}
