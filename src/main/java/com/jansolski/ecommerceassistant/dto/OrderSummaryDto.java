package com.jansolski.ecommerceassistant.dto;

import com.jansolski.ecommerceassistant.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO podsumowania zamówienia (krótka wersja)")
public class OrderSummaryDto {

    @Schema(description = "Unikalny identyfikator zamówienia", example = "1")
    private Long id;

    @Schema(description = "Łączna cena zamówienia", example = "1234.56")
    private BigDecimal totalPrice;

    @Schema(description = "Data utworzenia zamówienia")
    private LocalDateTime createdAt;

    @Schema(description = "Status zamówienia", example = "PROCESSING")
    private OrderStatus status;
}
