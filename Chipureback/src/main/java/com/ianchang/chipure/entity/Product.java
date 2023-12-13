package com.ianchang.chipure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private Integer productId;
    private String productName;
    private Integer productCategory;
    private String productIntro;
    private String productPicture;
    private Double productPrice;
    private Integer productSales;
}
// product_id product_category product_nameproduct_introproduct_priceproduct_sales