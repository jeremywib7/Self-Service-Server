package com.j23.server.repos.product;

import com.j23.server.models.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(String id);

    Page<Product> findAllByTotalCaloriesBetweenAndUnitPriceBetween(Long minCalories,
                                                                   Long maxCalories, BigDecimal minPrice,
                                                                   BigDecimal maxPrice,
                                                                   Pageable pageable);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Iterable<Product> findAllByNameContaining(String name);

    Page<Product> findAllByCategoryIdAndTotalCaloriesBetweenAndUnitPriceBetween(String id, Long minCalories,
                                                                                Long maxCalories, BigDecimal minPrice,
                                                                                BigDecimal maxPrice,
                                                                                Pageable pageable);

    boolean existsByName(String name);

}
