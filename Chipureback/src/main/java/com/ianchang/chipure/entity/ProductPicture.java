package com.ianchang.chipure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProductPicture {
    @Id
    private Integer id;
    private Integer productId;
    private String productPicture;
    private String intro;
}
