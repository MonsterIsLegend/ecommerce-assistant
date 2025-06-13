package com.jansolski.ecommerceassistant.service;

import com.jansolski.ecommerceassistant.dto.AddressDto;
import com.jansolski.ecommerceassistant.entity.Address;
import com.jansolski.ecommerceassistant.mapper.AddressMapper;
import com.jansolski.ecommerceassistant.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    public AddressDto getById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Address with that Id doesn't exist: " + id));
        return addressMapper.toDto(address);
    }

    public AddressDto create(AddressDto dto) {
        Address address = addressMapper.toEntity(dto);
        Address saved = addressRepository.save(address);
        return addressMapper.toDto(saved);
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
