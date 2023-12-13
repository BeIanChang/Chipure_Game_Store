package com.ianchang.chipure.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPromoProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/getPromoProduct")
                        .param("category_id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products", Matchers.hasSize(1)));
    }

    @Test
    public void testGetDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/getDetails")
                        .param("product_id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product").exists());
    }

    @Test
    public void testGetAllProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/getAllProduct"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.product").isArray());
    }

    @Test
    public void testGetProductBySearch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/getProductBySearch")
                        .param("search", "充电宝")
                        .param("pageSize", "5")
                        .param("currentPage", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
}
