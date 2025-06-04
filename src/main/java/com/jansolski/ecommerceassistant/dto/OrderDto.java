package com.jansolski.ecommerceassistant.dto;

import com.jansolski.ecommerceassistant.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO reprezentujący szczegóły zamówienia")
public class OrderDto {

    @Schema(description = "Unikalny identyfikator zamówienia", example = "1")
    private Long id;

    @Schema(description = "Klient, który złożył zamówienie")
    private CustomerDto customer;

    @Schema(description = "Lista produktów w zamówieniu")
    private List<ProductDto> products;

    @Schema(description = "Łączna cena zamówienia", example = "1234.56")
    private BigDecimal totalPrice;

    @Schema(description = "Data utworzenia zamówienia")
    private LocalDateTime createdAt;

    @Schema(description = "Status zamówienia", example = "PROCESSING")
    private OrderStatus status;
}
