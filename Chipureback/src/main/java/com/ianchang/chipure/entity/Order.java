package com.ianchang.chipure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Order {
    @Id
    private Integer id;
    private Integer orderId;
    private Integer userId;
    private Integer productId;
    private Integer productNum;
    private Double productPrice;
    private Integer orderTime;
}
