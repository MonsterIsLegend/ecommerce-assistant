package com.jansolski.ecommerceassistant.entity;

import com.jansolski.ecommerceassistant.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    private String cpu;

    private String gpu;

    private Integer ram;

    private Integer storage;

    private String screenSize;

    private String resolution;

    private Boolean touchscreen;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    @Column(length = 2000)
    private String description;
}
