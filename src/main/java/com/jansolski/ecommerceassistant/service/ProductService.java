package com.jansolski.ecommerceassistant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansolski.ecommerceassistant.dto.ProductDto;
import com.jansolski.ecommerceassistant.dto.ProductFilterDto;
import com.jansolski.ecommerceassistant.entity.Product;
import com.jansolski.ecommerceassistant.enums.ProductCategory;
import com.jansolski.ecommerceassistant.mapper.ProductMapper;
import com.jansolski.ecommerceassistant.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    public List<ProductDto> filterSearchSort(ProductFilterDto req) {
        List<Product> products = productRepository.findAll();

        if (req.getSearchQuery() != null && !req.getSearchQuery().isEmpty()) {
            String q = req.getSearchQuery().toLowerCase();
            products = products.stream()
                    .filter(p -> p.getBrand().toLowerCase().contains(q) ||
                            p.getModel().toLowerCase().contains(q) ||
                            (p.getDescription() != null && p.getDescription().toLowerCase().contains(q)))
                    .collect(Collectors.toList());
        }

        if (req.getFilters() != null && !req.getFilters().isEmpty()) {
            for (Map.Entry<String, String> entry : req.getFilters().entrySet()) {
                String field = entry.getKey();
                String value = entry.getValue();
                products = products.stream()
                        .filter(p -> filterByField(p, field, value))
                        .collect(Collectors.toList());
            }
        }

        String sortBy = req.getSortBy() != null ? req.getSortBy() : "model";
        String sortOrder = req.getSortOrder() != null ? req.getSortOrder() : "asc";
        Comparator<Product> comparator = getComparator(sortBy);
        if (comparator != null) {
            if ("desc".equalsIgnoreCase(sortOrder)) {
                comparator = comparator.reversed();
            }
            products.sort(comparator);
        }

        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    private boolean filterByField(Product p, String field, String value) {
        switch (field) {
            case "brand": return p.getBrand().equalsIgnoreCase(value);
            case "model": return p.getModel().equalsIgnoreCase(value);
            case "cpu": return p.getCpu() != null && p.getCpu().equalsIgnoreCase(value);
            case "gpu": return p.getGpu() != null && p.getGpu().equalsIgnoreCase(value);
            case "ram": return p.getRam() != null && p.getRam().toString().equals(value);
            case "storage": return p.getStorage() != null && p.getStorage().toString().equals(value);
            case "screenSize": return p.getScreenSize() != null && p.getScreenSize().equalsIgnoreCase(value);
            case "resolution": return p.getResolution() != null && p.getResolution().equalsIgnoreCase(value);
            case "touchscreen": return p.getTouchscreen() != null && p.getTouchscreen().toString().equalsIgnoreCase(value);
            case "price": return p.getPrice() != null && p.getPrice().toString().equals(value);
            case "category": return p.getCategory() != null && p.getCategory().name().equalsIgnoreCase(value);
            default: return true;
        }
    }

    private Comparator<Product> getComparator(String sortBy) {
        switch (sortBy) {
            case "price": return Comparator.comparing(Product::getPrice, Comparator.nullsLast(BigDecimal::compareTo));
            case "model": return Comparator.comparing(Product::getModel, String.CASE_INSENSITIVE_ORDER);
            case "brand": return Comparator.comparing(Product::getBrand, String.CASE_INSENSITIVE_ORDER);
            case "ram": return Comparator.comparing(Product::getRam, Comparator.nullsLast(Integer::compareTo));
            case "storage": return Comparator.comparing(Product::getStorage, Comparator.nullsLast(Integer::compareTo));
            case "screenSize": return Comparator.comparing(Product::getScreenSize, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
            case "category": return Comparator.comparing(p -> p.getCategory() != null ? p.getCategory().name() : "", String.CASE_INSENSITIVE_ORDER);
            case "id": return Comparator.comparing(Product::getId);
            default: return null;
        }
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
            throw new RuntimeException("Error durning test data loading: " + e.getMessage(), e);
        }
    }

}