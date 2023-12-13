package com.ianchang.chipure.controller;

import com.ianchang.chipure.entity.Carousel;
import com.ianchang.chipure.repository.CarouselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselRepository carouselRepository;

    @PostMapping("/getCarousel")
    public Map<String, Object> getCarousel(){
        Map<String, Object> response = new HashMap<>();
        List<Carousel> carouselList = carouselRepository.findAll();
        response.put("code", "001");
        response.put("carousel", carouselList);
        return response;
    }
}
