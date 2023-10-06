package com.rest.study.image.freeImage.service;

import com.rest.study.board.freeboard.entity.FreeBoard;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FreeImageService {

    Object uploadFile(MultipartFile file, FreeBoard freeBoard) throws IOException;
}
