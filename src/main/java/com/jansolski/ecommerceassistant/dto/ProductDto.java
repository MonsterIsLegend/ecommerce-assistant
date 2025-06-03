package com.jansolski.ecommerceassistant.dto;

import com.jansolski.ecommerceassistant.entity.ProductCategory;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    @NotBlank(message = "Marka nie może być pusta")
    private String brand;

    @NotBlank(message = "Model nie może być pusty")
    private String model;

    @NotBlank(message = "CPU nie może być puste")
    private String cpu;

    private String gpu;

    @NotNull(message = "RAM jest wymagany")
    @Min(value = 2, message = "RAM musi mieć co najmniej 2 GB")
    private Integer ram;

    @NotNull(message = "Pojemność dysku jest wymagana")
    @Min(value = 64, message = "Dysk musi mieć co najmniej 64 GB")
    private Integer storage;

    @NotBlank(message = "Rozmiar ekranu nie może być pusty")
    private String screenSize;

    @NotBlank(message = "Rozdzielczość nie może być pusta")
    private String resolution;

    @NotNull(message = "Pole touchscreen jest wymagane")
    private Boolean touchscreen;

    @NotNull(message = "Cena jest wymagana")
    @DecimalMin(value = "0.0", inclusive = false, message = "Cena musi być większa niż 0")
    private BigDecimal price;

    @NotNull(message = "Kategoria jest wymagana")
    private ProductCategory category;

    @Size(max = 2000, message = "Opis może mieć maksymalnie 2000 znaków")
    private String description;
}