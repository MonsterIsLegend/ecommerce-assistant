package com.jansolski.ecommerceassistant.mapper;

import com.jansolski.ecommerceassistant.dto.AddressDto;
import com.jansolski.ecommerceassistant.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);

    Address toEntity(AddressDto dto);
}
