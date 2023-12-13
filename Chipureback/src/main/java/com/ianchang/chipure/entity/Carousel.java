package com.ianchang.chipure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Carousel {
    @Id
    private Integer carouselId;
    private String imgPath;
}
