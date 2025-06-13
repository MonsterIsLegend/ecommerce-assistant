package com.jansolski.ecommerceassistant.service;

import com.jansolski.ecommerceassistant.dto.OrderDto;
import com.jansolski.ecommerceassistant.entity.Order;
import com.jansolski.ecommerceassistant.mapper.OrderMapper;
import com.jansolski.ecommerceassistant.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order with that id doesnt exists: " + id));
        return orderMapper.toDto(order);
    }

    public List<OrderDto> getByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderDto create(OrderDto dto) {
        Order order = orderMapper.toEntity(dto);
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
