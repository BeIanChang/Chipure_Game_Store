package com.ianchang.chipure.controller;

import com.ianchang.chipure.entity.Carousel;
import com.ianchang.chipure.entity.Product;
import com.ianchang.chipure.entity.ShoppingCart;
import com.ianchang.chipure.entity.ShoppingCartData;
import com.ianchang.chipure.repository.CarouselRepository;
import com.ianchang.chipure.repository.ProductRepository;
import com.ianchang.chipure.repository.ShoppingCartRepository;
import com.ianchang.chipure.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.ianchang.chipure.util.Util.checkLogin;

@RestController
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @PostMapping("/getShoppingCart")
    public Map<String, Object> getShoppingCart(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Integer userId = (Integer) requestBody.get("user_id");
        if (!checkLogin(request, userId)) {
            return null;
        }
        Map<String, Object> response = new HashMap<>();
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByUserId(userId);
        List<ShoppingCartData> shoppingCartDataList = new ArrayList<>();
        for(ShoppingCart tempShoppingCart:shoppingCartList)
        {
            Product tempProduct = productRepository.findByProductId(tempShoppingCart.getProductId());
            ShoppingCartData shoppingCartData = new ShoppingCartData(tempShoppingCart.getId(), tempProduct.getProductId(), tempProduct.getProductName(), tempProduct.getProductPicture(), tempProduct.getProductPrice(), tempShoppingCart.getNum());
            shoppingCartDataList.add(shoppingCartData);
        }
        response.put("code", "001");
        response.put("shoppingCartData", shoppingCartDataList);
        return response;
    }

    @PostMapping("/addShoppingCart")
    public Map<String, Object> addShoppingCart(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        Integer userId = (Integer) requestBody.get("user_id");
        Integer productId = (Integer) requestBody.get("product_id");

        if (!checkLogin(request, userId)) {
            return null;
        }
        // Check if the product is already in the shopping cart
        ShoppingCart existingCartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);
        if (existingCartItem != null) {
            // If the product already exists, increase the quantity
            int newQuantity = existingCartItem.getNum() + 1;
            if (newQuantity > 5) {
                response.put("code", "003");
                response.put("msg", "商品已达到购物限额");
                return response;
            }

            existingCartItem.setNum(newQuantity);
            shoppingCartRepository.save(existingCartItem);

            response.put("code", "002");
            response.put("msg", "商品已在购物车，数量+1");
            return response;
        }

        // If the product does not exist in the shopping cart, create a new entry
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            response.put("code", "004");
            response.put("msg", "商品不存在");
            return response;
        }

        ShoppingCart newCartItem = new ShoppingCart();
        newCartItem.setUserId(userRepository.findByUserId(userId));
        newCartItem.setProductId(product.getProductId());
        newCartItem.setNum(1);
        shoppingCartRepository.save(newCartItem);

        ShoppingCartData newShopingCartData = new ShoppingCartData(newCartItem.getId(), productId, product.getProductName(), product.getProductPicture(), product.getProductPrice(), newCartItem.getNum());

        response.put("code", "001");
        response.put("msg", "添加购物车成功");
        response.put("shoppingCartData", newShopingCartData);
        return response;
    }

    @PostMapping("/deleteShoppingCart")
    public Map<String, Object> deleteShoppingCart(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        Integer userId = (Integer) requestBody.get("user_id");
        Integer productId = (Integer) requestBody.get("product_id");

        if (!checkLogin(request, userId)) {
            return null;
        }
        ShoppingCart cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            shoppingCartRepository.delete(cartItem);
            response.put("code", "001");
            response.put("msg", "删除购物车成功");
        } else {
            response.put("code", "002");
            response.put("msg", "该商品不在购物车");
        }
        return response;
    }

    @PostMapping("/updateShoppingCart")
    public Map<String, Object> updateShoppingCart(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        Integer userId = (Integer) requestBody.get("user_id");
        Integer productId = (Integer) requestBody.get("product_id");
        Integer num = (Integer) requestBody.get("num");

        if (!checkLogin(request, userId)) {
            return null;
        }
        ShoppingCart cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem == null) {
            response.put("code", "002");
            response.put("msg", "该商品不在购物车");
            return response;
        }

        if (num < 1 || num > 5) {
            response.put("code", "004");
            response.put("msg", "数量不合法");
            return response;
        }

        if (num == cartItem.getNum()) {
            response.put("code", "003");
            response.put("msg", "数量没有发生变化");
            return response;
        }

        cartItem.setNum(num);
        shoppingCartRepository.save(cartItem);

        response.put("code", "001");
        response.put("msg", "修改购物车数量成功");
        return response;
    }
}


