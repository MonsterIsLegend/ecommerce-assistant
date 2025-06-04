package com.jansolski.ecommerceassistant.repository;

import com.jansolski.ecommerceassistant.entity.Product;
import com.jansolski.ecommerceassistant.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(ProductCategory category);

    List<Product> findByBrandIgnoreCase(String brand);

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
            "LOWER(p.model) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Product> searchByQuery(@Param("q") String query);

    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();

}
