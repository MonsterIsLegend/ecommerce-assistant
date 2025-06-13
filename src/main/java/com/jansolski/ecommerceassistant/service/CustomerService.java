package com.jansolski.ecommerceassistant.service;

import com.jansolski.ecommerceassistant.dto.AddressDto;
import com.jansolski.ecommerceassistant.dto.CustomerDto;
import com.jansolski.ecommerceassistant.entity.Address;
import com.jansolski.ecommerceassistant.entity.Customer;
import com.jansolski.ecommerceassistant.mapper.AddressMapper;
import com.jansolski.ecommerceassistant.mapper.CustomerMapper;
import com.jansolski.ecommerceassistant.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;

    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer with that Id not found: " + id));
        return customerMapper.toDto(customer);
    }

    public CustomerDto create(CustomerDto dto) {
        Customer customer = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDto(saved);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto addAddress(Long customerId, AddressDto dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        boolean hasMain = customer.getAddresses().stream()
                .anyMatch(Address::isMain);

        if (dto.isMain() && hasMain) {
            throw new IllegalStateException("Customer already has a primary address!");
        }

        Address address = addressMapper.toEntity(dto);
        address.setCustomer(customer);
        customer.getAddresses().add(address);

        Customer updated = customerRepository.save(customer);
        return customerMapper.toDto(updated);
    }


    public CustomerDto updateAddress(Long customerId, Long addressId, AddressDto dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        Address address = customer.getAddresses().stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Address not found"));

        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());

        if (dto.isMain()) {
            customer.getAddresses().forEach(a -> a.setMain(false));
            address.setMain(true);
        } else {
            address.setMain(false);
        }

        Customer updated = customerRepository.save(customer);
        return customerMapper.toDto(updated);
    }

    public CustomerDto deleteAddress(Long customerId, Long addressId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        boolean removed = customer.getAddresses().removeIf(a -> a.getId().equals(addressId));
        if (!removed) throw new NoSuchElementException("Address not found");

        Customer updated = customerRepository.save(customer);
        return customerMapper.toDto(updated);
    }

}
