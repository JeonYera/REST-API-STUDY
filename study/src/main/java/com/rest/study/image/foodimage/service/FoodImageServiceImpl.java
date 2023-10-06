package com.rest.study.image.foodimage.service;

import com.rest.study.board.foodboard.entity.FoodBoard;
import com.rest.study.image.foodimage.entity.FoodImageAttachment;
import com.rest.study.image.foodimage.repository.FoodImageRepository;
import com.rest.study.common.controller.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FoodImageServiceImpl implements FoodImageService {

    @Autowired
    private FoodImageRepository imageRepository;

    @Value("${image.upload.directory}")
    private String imageUploadDir;

    private Path fileDir;

    private final String TYPE_CSV = "text/csv";

    @PostConstruct
    public void postConstruct() {
        fileDir = Paths.get(imageUploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileDir);
        } catch (IOException e) {
            log.error("에러 = {}", e.getMessage());
        }
    }

    @Override
    public FoodImageAttachment uploadFile(MultipartFile images, FoodBoard foodBoard) {
        String uniqueName = StringUtils.generateUniqueName(images.getOriginalFilename());
        Path targetLocation = fileDir.resolve(uniqueName);
        try(InputStream inputStream = images.getInputStream()) {
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FoodImageAttachment image = FoodImageAttachment.builder()
                .originName(images.getOriginalFilename())
                .uniqueName(uniqueName)
                .foodBoard(foodBoard)
                .imageFileSize(images.getSize())
                .build();
        return  imageRepository.saveAndFlush(image);
    }

    @Override
    public void updateFile(MultipartFile images, FoodBoard foodBoard) {
        String uniqueName = StringUtils.generateUniqueName(images.getOriginalFilename());
        FoodImageAttachment existingImage = imageRepository.findByUniqueName(uniqueName);
        if (existingImage != null) {
            try (InputStream inputStream = images.getInputStream()) {
                Path targetLocation = fileDir.resolve(uniqueName);
                Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
                existingImage.setOriginName(images.getOriginalFilename());
                existingImage.setImageFileSize(images.getSize());
                existingImage.setFoodBoard(foodBoard);
                imageRepository.saveAndFlush(existingImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            uploadFile(images, foodBoard);
        }
    }
}
