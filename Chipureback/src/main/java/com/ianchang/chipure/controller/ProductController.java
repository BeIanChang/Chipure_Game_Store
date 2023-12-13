package com.ianchang.chipure.controller;

import com.ianchang.chipure.entity.Category;
import com.ianchang.chipure.entity.Product;
import com.ianchang.chipure.entity.ProductPicture;
import com.ianchang.chipure.repository.CategoryRepository;
import com.ianchang.chipure.repository.ProductPictureRepository;
import com.ianchang.chipure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductPictureRepository productPictureRepository;

    @PostMapping("/getPromoProduct")
    public Map<String, Object> getPromoProduct(@RequestParam("category_id") Integer category_id) {
        Map<String, Object> response = new HashMap<>();
        List<Product> promoProducts = productRepository.findByProductCategory(category_id);
        response.put("code", "001");
        response.put("product", promoProducts.subList(0, Math.min(2, promoProducts.size())));
        return response;
    }

    @PostMapping("/getDetails")
    public Map<String, Object> getDetails(@RequestParam("product_id") Integer product_id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Product> product = productRepository.findById(product_id);
        if (product.isPresent()) {
            response.put("code", "001");
            response.put("product", product.get());
        } else {
            response.put("code", "004");
            response.put("error", "Product not found");
        }
        return response;
    }

    @PostMapping("/getAllProduct")
    public Map<String, Object> getAllProduct(@RequestParam("category_id") Integer category_id) {
        Map<String, Object> response = new HashMap<>();
        List<Product> products;
        if (category_id == null) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findByProductCategory(category_id);
        }
        response.put("code", 001);
        response.put("product", products);
        response.put("total", products.size());
        return response;
    }

    @PostMapping("/getProductBySearch")
    public Map<String, Object> getProductBySearch(@RequestParam("search") String search, @RequestParam("pageSize") int pageSize, @RequestParam("currentPage") int currentPage) {
        // 根据搜索条件查询商品
        List<Product> productList = productRepository.findByProductNameContaining(search);
        Map<String, Object> response = new HashMap<>();

        // 计算分页参数
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, productList.size());

        // 获取指定范围的子列表作为当前页的商品数据
        List<Product> currentPageList = productList.subList(startIndex, endIndex);
        response.put("code", "001");
        response.put("product", currentPageList);
        response.put("total", endIndex-startIndex);
        return response;
    }

    @PostMapping("/getCategory")
    public Map<String, Object> getCategory(){
        Map<String, Object> response = new HashMap<>();
        List<Category> categoryList = categoryRepository.findAll();
        response.put("code", "001");
        response.put("category", categoryList);
        return response;
    }

    @PostMapping("/getDetailsPicture")
    public Map<String, Object> getDetailsPicture(@RequestParam("productID") Integer productID){
        Map<String, Object> response = new HashMap<>();
        List<Category> ProductPicture = productPictureRepository.findByProductId(productID);
        response.put("code", "001");
        response.put("ProductPicture", ProductPicture);
        return response;
    }
}