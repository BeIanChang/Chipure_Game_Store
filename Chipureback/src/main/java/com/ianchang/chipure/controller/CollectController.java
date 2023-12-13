package com.ianchang.chipure.controller;

import com.ianchang.chipure.entity.Collect;
import com.ianchang.chipure.repository.CollectRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ianchang.chipure.util.Util.checkLogin;

@RestController
@RequestMapping("user/collect")
public class CollectController {
    @Autowired
    private CollectRepository collectRepository;

    @PostMapping("/getCollect")
    public Map<String, Object> getCollect(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Integer userId = (Integer) requestBody.get("user_id");

        // 校验用户是否登录
        if (!checkLogin(request, userId)) {
            return null;
        }
        // 查询收藏
        List<Collect> collectList = collectRepository.findByUserId(userId);

        // 返回结果
        Map<String, Object> response = new HashMap<>();
        if (collectList.isEmpty()) {
            response.put("code", "002");
            response.put("msg", "该用户没有收藏的商品");
        } else {
            response.put("code", "001");
            response.put("collectList", collectList);
        }
        return response;
    }

    @PostMapping("/addCollect")
    public Map<String, Object> addCollect(@RequestBody Map<String, Object> requestBody) {
        int userId = (int) requestBody.get("user_id");
        int productId = (int) requestBody.get("product_id");

        // 检查商品是否已经收藏
        Collect existingCollect = collectRepository.findByUserIdAndProductId(userId, productId);
        if (existingCollect != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", "003");
            response.put("msg", "该商品已经添加收藏，请到我的收藏查看");
            return response;
        }

        // 创建收藏
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setProductId(productId);
        collect.setCollectTime((int) System.currentTimeMillis());

        // 保存收藏
        Collect savedCollect = collectRepository.save(collect);

        // 返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("code", "001");
        response.put("msg", "添加收藏成功");
        return response;
    }

    @PostMapping("/deleteCollect")
    public Map<String, Object> deleteCollect(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        int userId = (int) requestBody.get("user_id");
        int productId = (int) requestBody.get("product_id");
        // 校验用户是否登录
        if (!checkLogin(request, userId)) {
            return null;
        }
        // 查询收藏
        Collect collect = collectRepository.findByUserIdAndProductId(userId, productId);

        // 检查收藏是否存在
        if (collect == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", "002");
            response.put("msg", "该商品不在收藏列表");
            return response;
        }

        // 删除收藏
        collectRepository.delete(collect);

        // 返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("code", "001");
        response.put("msg", "删除收藏成功");
        return response;
    }
}
