package com.rest.study.image.service;

import com.rest.study.board.entity.Board;
import com.rest.study.image.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<Image> uploadFile(List<MultipartFile> images, Board board) throws IOException;
}
