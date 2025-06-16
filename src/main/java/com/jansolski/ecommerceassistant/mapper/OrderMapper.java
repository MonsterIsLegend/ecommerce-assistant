package com.jansolski.ecommerceassistant.mapper;

import com.jansolski.ecommerceassistant.dto.OrderDto;
import com.jansolski.ecommerceassistant.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface OrderMapper {

    OrderDto toDto(Order order);

    Order toEntity(OrderDto dto);
}
