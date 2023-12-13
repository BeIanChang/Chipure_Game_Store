package com.ianchang.chipure.controller;

import com.ianchang.chipure.entity.User;
import com.ianchang.chipure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        Map<String, Object> response = new HashMap<>();

        // 根据用户名查询用户
        Optional<User> user = userRepository.findByUserName(userName);

        String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);

        if(!matcher.matches())
        {
            // 用户名不符合规则
            response.put("code", "002");
            response.put("msg", "用户名不合法(以字母开头，允许5-16字节，允许字母数字下划线)");
        }
        else if (!user.isPresent() || !user.get().getPassword().equals(password)) {
            // 用户名或密码错误
            response.put("code", "004");
            response.put("msg", "登录失败，用户名或密码错误");
        } else {
            // 登录成功
            Map<String, Object> data = new HashMap<>();
            data.put("user_id", user.get().getUserId());
            data.put("user_name", user.get().getUserName());
            response.put("code", "001");
            response.put("user", data);
            response.put("msg", "登录成功");
        }

        return response;
    }

    @PostMapping("/findUserName")
    public Map<String, Object> findUserName(@RequestParam("userName") String userName){
        Map<String, Object> response = new HashMap<>();

        // 根据用户名查询用户
        Optional<User> user = userRepository.findByUserName(userName);

        if (!user.isPresent()){
            response.put("code", 004);
            response.put("msg", "该用户不存在！");
        } else {
            response.put("code", 001);
            response.put("msg", "该用户存在。");
        }
        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam("userName") String userName, @RequestParam("password") String password){
        Map<String, Object> response = new HashMap<>();

        String regex1 = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
        String regex2 = "^[a-zA-Z0-9_][a-zA-Z0-9_]{4,15}$";
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex1);

        Matcher matcher1 = pattern1.matcher(userName);
        Matcher matcher2 = pattern2.matcher(password);

        // 根据用户名查询用户
        Optional<User> user = userRepository.findByUserName(userName);

        if (!user.isPresent()){
            if(!matcher1.matches())
            {
                response.put("code", 002);
                response.put("msg", "用户名不合法（以字母开头，允许5-16字节，允许字母数字下划线），注册失败！");
            }
            else if(!matcher2.matches())
            {
                response.put("code", 003);
                response.put("msg", "密码不合法（以字母或数字开头，允许5-16字节，允许字母数字下划线），注册失败！");
            }
            else
            {
                User newUser = new User();
                newUser.setUserName(userName);
                newUser.setPassword(password);
                userRepository.save(newUser);
                response.put("code", 001);
                response.put("msg", "注册成功！");
            }
        } else {
            response.put("code", 004);
            response.put("msg", "该用户存在，注册失败！");
        }
        return response;
    }
}

