package com.rest.study.image.foodimage.controller;

import com.rest.study.image.foodimage.service.FoodImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/image")
public class FoodImageController {

    @Autowired
    private FoodImageService imageService;

}
