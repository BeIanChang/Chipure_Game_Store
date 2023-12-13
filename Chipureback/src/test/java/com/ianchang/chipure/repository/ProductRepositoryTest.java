package com.ianchang.chipure.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import com.ianchang.chipure.repository.ProductRepository;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void findAll(){
        System.out.println(productRepository.findAll());
    }
    @Test
    void login(){

    }
}