package com.ianchang.chipure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // 进行初始化设置，例如准备测试数据
    }

    @Test
    public void testGetCollect() throws Exception {
        // 构建请求参数
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_id", 123);

        // 发起请求并验证结果
        mockMvc.perform(MockMvcRequestBuilders.post("/user/collect/getCollect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("001"));
    }

    @Test
    public void testAddCollect() throws Exception {
        // 构建请求参数
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_id", 123);
        requestBody.put("product_id", 456);

        // 发起请求并验证结果
        mockMvc.perform(MockMvcRequestBuilders.post("/user/collect/addCollect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("001"));
    }

    @Test
    public void testDeleteCollect() throws Exception {
        // 构建请求参数
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_id", 123);
        requestBody.put("product_id", 456);

        // 发起请求并验证结果
        mockMvc.perform(MockMvcRequestBuilders.post("/user/collect/deleteCollect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("001"));
    }
}
