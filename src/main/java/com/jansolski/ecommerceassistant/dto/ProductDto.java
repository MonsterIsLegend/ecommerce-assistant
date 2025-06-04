package com.jansolski.ecommerceassistant.dto;

import com.jansolski.ecommerceassistant.enums.ProductCategory;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    @Schema(description = "Unikalny identyfikator produktu", example = "1")
    private Long id;

    @NotBlank(message = "Marka nie może być pusta")
    @Schema(description = "Marka laptopa", example = "Dell", required = true)
    private String brand;

    @NotBlank(message = "Model nie może być pusty")
    @Schema(description = "Model laptopa", example = "XPS 15", required = true)
    private String model;

    @NotBlank(message = "CPU nie może być puste")
    @Schema(description = "Procesor laptopa", example = "Intel Core i7-12700H", required = true)
    private String cpu;

    @Schema(description = "Karta graficzna laptopa", example = "NVIDIA RTX 3050")
    private String gpu;

    @NotNull(message = "RAM jest wymagany")
    @Min(value = 2, message = "RAM musi mieć co najmniej 2 GB")
    @Schema(description = "Pamięć RAM w GB", example = "16", required = true)
    private Integer ram;

    @NotNull(message = "Pojemność dysku jest wymagana")
    @Min(value = 64, message = "Dysk musi mieć co najmniej 64 GB")
    @Schema(description = "Pojemność dysku w GB", example = "512", required = true)
    private Integer storage;

    @NotBlank(message = "Rozmiar ekranu nie może być pusty")
    @Schema(description = "Rozmiar ekranu", example = "15.6\"", required = true)
    private String screenSize;

    @NotBlank(message = "Rozdzielczość nie może być pusta")
    @Schema(description = "Rozdzielczość ekranu", example = "1920x1080", required = true)
    private String resolution;

    @NotNull(message = "Pole touchscreen jest wymagane")
    @Schema(description = "Czy laptop ma ekran dotykowy", example = "false", required = true)
    private Boolean touchscreen;

    @NotNull(message = "Cena jest wymagana")
    @DecimalMin(value = "0.0", inclusive = false, message = "Cena musi być większa niż 0")
    @Schema(description = "Cena produktu w PLN", example = "4999.99", required = true)
    private BigDecimal price;

    @NotNull(message = "Kategoria jest wymagana")
    @Schema(description = "Kategoria produktu", example = "GAMING", required = true)
    private ProductCategory category;

    @Size(max = 2000, message = "Opis może mieć maksymalnie 2000 znaków")
    @Schema(description = "Opis produktu", example = "Ultramobilny laptop gamingowy z wydajnym CPU i ekranem 165Hz")
    private String description;
}
