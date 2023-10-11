package com.rest.study.image.service;

import com.rest.study.board.entity.Board;
import com.rest.study.image.entity.Image;
import com.rest.study.image.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Value("${image.upload.directory}")
    private String imageUploadDirectory; // 이미지 파일 저장 경로
    private Path fileDir;
    private final String TYPE_CSV = "text/csv";
    private String dir = "/tmp";

    @PostConstruct
    public void postConstruct() {
        fileDir = Paths.get(imageUploadDirectory).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileDir);
        } catch (IOException e) {
            log.error("이미지 업로드 디렉토리를 생성하지 못했습니다.: {}", e.getMessage());
        }
    }

    public List<Image> uploadFile(List<MultipartFile> images, Board board) throws IOException {
        List<Image> attachments = new ArrayList<>();

        for (MultipartFile file : images) {
            // 파일 유형 검증
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image")) {
                throw new IllegalArgumentException("이미지 파일을 확인해주세요.");
            }

            String uploadFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = StringUtils.getFilenameExtension(uploadFileName); // 확장자 추출
            String realName = UUID.randomUUID().toString() + "." + fileExtension; // UUID와 확장자 합치기
            Path targetLocation = fileDir.resolve(realName);

            // 파일 저장
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }

            Image image = new Image();
            image.setOriginName(uploadFileName);
            image.setUniqueName(realName);
            image.setImageFileSize(file.getSize());

            image.setBoard(board);

            attachments.add(imageRepository.save(image));
        }
        return attachments;
    }

}
