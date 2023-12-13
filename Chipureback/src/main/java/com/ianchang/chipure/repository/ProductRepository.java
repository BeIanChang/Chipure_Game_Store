package com.ianchang.chipure.repository;

import com.ianchang.chipure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategory(Integer categoryId);

    List<Product> findByProductNameContaining(String Name);

    Product findByProductId(Integer productId);
}
