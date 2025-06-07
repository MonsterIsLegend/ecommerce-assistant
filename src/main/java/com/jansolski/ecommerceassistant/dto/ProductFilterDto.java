package com.jansolski.ecommerceassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO reprezentujący obiekt po którym będziemy filtrować")
public class ProductFilterDto {
    private String searchQuery;
    private Map<String, String> filters;
    private String sortBy;
    private String sortOrder;
}
