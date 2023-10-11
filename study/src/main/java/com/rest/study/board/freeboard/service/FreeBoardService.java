package com.rest.study.board.freeboard.service;

import com.rest.study.board.freeboard.dto.FreeBoardCreateDto;
import com.rest.study.board.freeboard.dto.FreeBoardReadDto;
import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FreeBoardService {
    List<FreeBoard> findAllByOrderByFreeIdDesc();

    FreeBoardReadDto findBoard(Long id);

    FreeBoardReadDto writeBoard(FreeBoardCreateDto freeBoardCreateDto) throws IOException;

    void deleteBoard(Long id);

    FreeBoardReadDto editBoard(Long id, FreeBoardCreateDto updateDto, User user, List<MultipartFile> images) throws IOException;

}
