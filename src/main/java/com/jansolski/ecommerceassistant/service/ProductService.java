package com.jansolski.ecommerceassistant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansolski.ecommerceassistant.dto.ProductDto;
import com.jansolski.ecommerceassistant.entity.Product;
import com.jansolski.ecommerceassistant.enums.ProductCategory;
import com.jansolski.ecommerceassistant.mapper.ProductMapper;
import com.jansolski.ecommerceassistant.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto getById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Produkt o ID " + id + " nie istnieje"));
    }

    public ProductDto create(ProductDto dto) {
        Product entity = productMapper.toEntity(dto);
        Product saved = productRepository.save(entity);
        return productMapper.toDto(saved);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> getByCategory(ProductCategory category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> search(String query) {
        return productRepository.searchByQuery(query)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getAllSortedByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getAllSortedByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addTestDataFromJson() {
        try (InputStream inputStream = getClass().getResourceAsStream("/testData/products.json")) {
            if (inputStream == null) throw new FileNotFoundException("products.json not found");

            ObjectMapper mapper = new ObjectMapper();
            List<ProductDto> products = mapper.readValue(
                    inputStream,
                    new TypeReference<List<ProductDto>>() {}
            );

            products.forEach(this::create);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas ładowania danych testowych: " + e.getMessage(), e);
        }
    }

}