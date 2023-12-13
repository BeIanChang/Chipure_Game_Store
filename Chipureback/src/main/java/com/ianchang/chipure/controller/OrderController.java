package com.ianchang.chipure.controller;

import com.ianchang.chipure.entity.Order;
import com.ianchang.chipure.entity.ShoppingCart;
import com.ianchang.chipure.repository.OrderRepository;
import com.ianchang.chipure.repository.ShoppingCartRepository;
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
@RequestMapping("/user/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    private ShoppingCartRepository shoppingCartRepository;

    @PostMapping("/addOrder")
    public Map<String, Object> addOrder(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Integer userId = (Integer) requestBody.get("user_id");
        List<Map<String, Object>> products = (List<Map<String, Object>>) requestBody.get("products");

        // 校验用户是否登录
        if (!checkLogin(request, userId)) {
            return null;
        }

        // 添加订单
        for (Map<String, Object> product : products) {
            Integer productId = (Integer) product.get("productID");
            Integer num = (Integer) product.get("num");

            // 创建订单项
            int currentTime = (int) System.currentTimeMillis();
            Order order = new Order();
            order.setOrderId(userId + currentTime);  // 生成订单号，具体实现需要根据业务逻辑确定
            order.setUserId(userId);
            order.setProductId(productId);
            order.setProductNum(num);
            order.setProductPrice((Double) product.get("price"));
            order.setOrderTime(currentTime);

            // 保存订单
            orderRepository.save(order);

            // 减少购物车中相应商品的数量
            ShoppingCart shoppingCart = shoppingCartRepository.findByUserIdAndProductId(userId, productId);
            if (shoppingCart != null) {
                int currentNum = shoppingCart.getNum();
                if (currentNum > num) {
                    // 更新购物车中商品数量
                    shoppingCart.setNum(currentNum - num);
                    shoppingCartRepository.save(shoppingCart);
                } else {
                    // 商品数量不足，从购物车中移除
                    shoppingCartRepository.delete(shoppingCart);
                }
            }
        }

        // 返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("code", "001");
        response.put("msg", "购买成功");
        return response;
    }

    @PostMapping("/getOrder")
    public Map<String, Object> getOrder(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Integer userId = (Integer) requestBody.get("user_id");
        List<Map<String, Object>> products = (List<Map<String, Object>>) requestBody.get("products");

        // 校验用户是否登录
        if (!checkLogin(request, userId)) {
            return null;
        }

        // 查询订单
        List<Order> orders = orderRepository.findByUserId(userId);

        // 返回结果
        Map<String, Object> response = new HashMap<>();
        if (orders.isEmpty()) {
            response.put("code", "002");
            response.put("msg", "该用户没有订单信息");
        } else {
            response.put("code", "001");
            response.put("orders", orders);
        }
        return response;
    }
}
