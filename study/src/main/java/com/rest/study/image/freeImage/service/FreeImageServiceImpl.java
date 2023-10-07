package com.rest.study.image.freeImage.service;

import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.image.freeImage.entity.FreeImageAttachment;
import com.rest.study.image.freeImage.repository.FreeImageRepository;
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
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class FreeImageServiceImpl implements FreeImageService {

    @Autowired
    private FreeImageRepository imageRepository;

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

    public FreeImageAttachment uploadFile(MultipartFile file, FreeBoard freeBoard) throws IOException {
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

        FreeImageAttachment image = new FreeImageAttachment();
        image.setOriginName(uploadFileName);
        image.setUniqueName(realName);
        image.setImageFileSize(file.getSize());

        image.setFreeBoard(freeBoard);

        return imageRepository.save(image);
    }
}
