package com.ianchang.chipure.repository;

import com.ianchang.chipure.entity.Category;
import com.ianchang.chipure.entity.Product;
import com.ianchang.chipure.entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPictureRepository extends JpaRepository<ProductPicture, Integer> {
    List<Category> findByProductId(Integer productID);
}
