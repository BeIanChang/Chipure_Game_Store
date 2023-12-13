package com.ianchang.chipure.repository;

import com.ianchang.chipure.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    List<ShoppingCart> findByUserId(Integer userId);

    ShoppingCart findByUserIdAndProductId(Integer userId, Integer productId);
}
