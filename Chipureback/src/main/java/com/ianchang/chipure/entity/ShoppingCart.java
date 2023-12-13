package com.ianchang.chipure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ShoppingCart {
    @Id
    private Integer Id;
    private Integer userId;
    private Integer productId;
    private Integer num;
}

