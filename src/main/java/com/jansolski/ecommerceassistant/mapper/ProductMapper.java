package com.jansolski.ecommerceassistant.mapper;

import com.jansolski.ecommerceassistant.dto.ProductDto;
import com.jansolski.ecommerceassistant.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
