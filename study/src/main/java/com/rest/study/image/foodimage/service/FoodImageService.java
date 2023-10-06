package com.rest.study.image.foodimage.service;

import com.rest.study.board.foodboard.entity.FoodBoard;
import com.rest.study.image.foodimage.entity.FoodImageAttachment;
import org.springframework.web.multipart.MultipartFile;

public interface FoodImageService {

    FoodImageAttachment uploadFile(MultipartFile file, FoodBoard foodBoard);

    void updateFile(MultipartFile images, FoodBoard foodBoard);
}
