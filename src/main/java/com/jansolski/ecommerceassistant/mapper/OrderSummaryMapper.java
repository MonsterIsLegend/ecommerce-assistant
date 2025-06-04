package com.jansolski.ecommerceassistant.mapper;

import com.jansolski.ecommerceassistant.dto.OrderSummaryDto;
import com.jansolski.ecommerceassistant.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderSummaryMapper {

    OrderSummaryDto toDto(Order order);
}
