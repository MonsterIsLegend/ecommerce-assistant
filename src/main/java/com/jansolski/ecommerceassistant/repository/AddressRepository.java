package com.jansolski.ecommerceassistant.repository;

import com.jansolski.ecommerceassistant.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
