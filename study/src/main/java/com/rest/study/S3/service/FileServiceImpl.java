package com.rest.study.S3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rest.study.board.entity.Board;
import com.rest.study.S3.entity.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService{

    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public FileServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileUrl = "https://" + bucket + ".s3.amazonaws.com/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        s3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
        return fileUrl;
    }
}