package com.ianchang.chipure.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ianchang.chipure.entity.User;
import com.ianchang.chipure.repository.UserRepository;
import com.ianchang.chipure.controller.UserController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void login_ValidCredentials_Success() throws Exception {
        // 模拟用户存在的情况
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("testPassword");
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/users/login")
                        .param("userName", "testUser")
                        .param("password", "testPassword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(001))
                .andExpect(jsonPath("$.msg").value("登录成功"));
    }

    @Test
    void login_InvalidCredentials_Failure() throws Exception {
        // 模拟用户不存在的情况
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.empty());

        mockMvc.perform(post("/users/login")
                        .param("userName", "testUser")
                        .param("password", "testPassword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(004))
                .andExpect(jsonPath("$.msg").value("登录失败，用户名或密码错误"));
    }

    @Test
    void findUserName_UserExists_Success() throws Exception {
        // 模拟用户存在的情况
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/users/findUserName")
                        .param("userName", "testUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(001))
                .andExpect(jsonPath("$.msg").value("该用户存在。"));
    }

    @Test
    void findUserName_UserNotExists_Success() throws Exception {
        // 模拟用户不存在的情况
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.empty());

        mockMvc.perform(post("/users/findUserName")
                        .param("userName", "testUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(004))
                .andExpect(jsonPath("$.msg").value("该用户不存在！"));
    }

    @Test
    void register_NewUser_Success() throws Exception {
        // 模拟用户不存在的情况
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.empty());

        mockMvc.perform(post("/users/register")
                        .param("userName", "testUser")
                        .param("password", "testPassword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(001))
                .andExpect(jsonPath("$.msg").value("注册成功！"));
    }

    @Test
    void register_ExistingUser_Failure() throws Exception {
        // 模拟用户存在的情况
        when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/users/register")
                        .param("userName", "testUser")
                        .param("password", "testPassword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(004))
                .andExpect(jsonPath("$.msg").value("该用户存在，注册失败！"));
    }
}

/*
class UserControllerTest {
    @SpringBootTest
    @AutoConfigureMockMvc
    class UserControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserRepository userRepository;

        @Test
        void login_ValidCredentials_Success() throws Exception {
            // 模拟用户存在的情况
            User user = new User();
            user.setUserName("testUser");
            user.setPassword("testPassword");
            when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(user));

            mockMvc.perform(post("/users/login")
                            .param("userName", "testUser")
                            .param("password", "testPassword"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(001))
                    .andExpect(jsonPath("$.msg").value("登录成功"));
        }

        @Test
        void login_InvalidCredentials_Failure() throws Exception {
            // 模拟用户不存在的情况
            when(userRepository.findByUserName("testUser")).thenReturn(Optional.empty());

            mockMvc.perform(post("/users/login")
                            .param("userName", "testUser")
                            .param("password", "testPassword"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(004))
                    .andExpect(jsonPath("$.msg").value("登录失败，用户名或密码错误"));
        }

        @Test
        void findUserName_UserExists_Success() throws Exception {
            // 模拟用户存在的情况
            when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(new User()));

            mockMvc.perform(post("/users/findUserName")
                            .param("userName", "testUser"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(001))
                    .andExpect(jsonPath("$.msg").value("该用户存在。"));
        }

        @Test
        void findUserName_UserNotExists_Success() throws Exception {
            // 模拟用户不存在的情况
            when(userRepository.findByUserName("testUser")).thenReturn(Optional.empty());

            mockMvc.perform(post("/users/findUserName")
                            .param("userName", "testUser"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(004))
                    .andExpect(jsonPath("$.msg").value("该用户不存在！"));
        }

        @Test
        void register_NewUser_Success() throws Exception {
            // 模拟用户不存在的情况
            when(userRepository.findByUserName("testUser")).thenReturn(Optional.empty());

            mockMvc.perform(post("/users/register")
                            .param("userName", "testUser")
                            .param("password", "testPassword"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(001))
                    .andExpect(jsonPath("$.msg").value("注册成功！"));
        }

        @Test
        void register_ExistingUser_Failure() throws Exception {
            // 模拟用户存在的情况
            when(userRepository.findByUserName("testUser")).thenReturn(Optional.of(new User()));

            mockMvc.perform(post("/users/register")
                            .param("userName", "testUser")
                            .param("password", "testPassword"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(004))
                    .andExpect(jsonPath("$.msg").value("该用户存在，注册失败！"));
        }
    }

}
*/