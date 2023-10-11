package com.rest.study.image.service;

import com.rest.study.board.entity.FreeBoard;
import com.rest.study.image.entity.ImageAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    ImageAttachment uploadFile(MultipartFile file, FreeBoard freeBoard) throws IOException;
}
