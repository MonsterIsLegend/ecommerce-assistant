package com.jansolski.ecommerceassistant.controller;

import com.jansolski.ecommerceassistant.dto.ProductDto;
import com.jansolski.ecommerceassistant.enums.ProductCategory;
import com.jansolski.ecommerceassistant.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        ProductDto created = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDto>> getByCategory(@PathVariable ProductCategory category) {
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<ProductDto>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productService.getByBrand(brand));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> search(@RequestParam String q) {
        return ResponseEntity.ok(productService.search(q));
    }

    @GetMapping("/sort/price/asc")
    public ResponseEntity<List<ProductDto>> sortByPriceAsc() {
        return ResponseEntity.ok(productService.getAllSortedByPriceAsc());
    }

    @GetMapping("/sort/price/desc")
    public ResponseEntity<List<ProductDto>> sortByPriceDesc() {
        return ResponseEntity.ok(productService.getAllSortedByPriceDesc());
    }

    @PostMapping("/addData")
    public ResponseEntity<String> addData() {
        productService.addTestDataFromJson();
        return ResponseEntity.ok("Dane testowe zostały załadowane.");
    }
}
