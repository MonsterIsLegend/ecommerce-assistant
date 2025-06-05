package com.jansolski.ecommerceassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO reprezentujący adres użytkownika")
public class AddressDto {

    @Schema(description = "Unikalny identyfikator adresu", example = "1")
    private Long id;

    @Schema(description = "Ulica", example = "ul. Marszałkowska 10")
    private String street;

    @Schema(description = "Miasto", example = "Warszawa")
    private String city;

    @Schema(description = "Kod pocztowy", example = "00-001")
    private String postalCode;

    @Schema(description = "Kraj", example = "Polska")
    private String country;

    @Schema(description = "Czy adres domyślny", example = "true")
    private boolean main;
}
