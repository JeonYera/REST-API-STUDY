package com.rest.study.image.freeImage.controller;

import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.image.freeImage.service.FreeImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/image")
public class FreeImageController {

    @Autowired
    private FreeImageService imageService;

    public FreeImageController(FreeImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam ("file")MultipartFile file, FreeBoard freeBoard) throws IOException {
        return ResponseEntity.ok(imageService.uploadFile(file, freeBoard));
    }
}
