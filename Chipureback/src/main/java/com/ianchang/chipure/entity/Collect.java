package com.ianchang.chipure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Collect {
    @Id
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer collectTime;
}
