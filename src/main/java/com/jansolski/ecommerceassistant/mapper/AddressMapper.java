package com.jansolski.ecommerceassistant.mapper;

import com.jansolski.ecommerceassistant.dto.AddressDto;
import com.jansolski.ecommerceassistant.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {


    AddressDto toDto(Address address);

    @Mapping(target = "customer", ignore = true)
    Address toEntity(AddressDto dto);
}
