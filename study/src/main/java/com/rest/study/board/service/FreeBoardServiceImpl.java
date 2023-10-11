package com.rest.study.board.service;

import com.rest.study.board.dto.FreeBoardCreateDto;
import com.rest.study.board.dto.FreeBoardReadDto;
import com.rest.study.board.entity.FreeBoard;
import com.rest.study.board.repository.FreeBoardRepository;
import com.rest.study.image.service.ImageService;
import com.rest.study.user.entity.User;
import com.rest.study.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FreeBoardServiceImpl implements FreeBoardService{

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Override
    public List<FreeBoard> findAllByOrderByFreeIdDesc() {
        return freeBoardRepository.findAllByOrderByFreeIdDesc();
    }

    @Override
    public FreeBoardReadDto findBoard(Long id) {
        return FreeBoardReadDto.toDto(freeBoardRepository.findById(id).orElse(null));
    }

    @Override
    public FreeBoardReadDto writeBoard(FreeBoardCreateDto freeBoardCreateDto) throws IOException {
        User user = userService.findByUserId(freeBoardCreateDto.getFreeUserId());
        FreeBoard freeBoard = FreeBoard.builder()
                .freeContent(freeBoardCreateDto.getFreeContent())
                .freeTitle(freeBoardCreateDto.getFreeTitle())
                .user(user)
                .freeCreatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        freeBoard = freeBoardRepository.save(freeBoard);

        if(freeBoardCreateDto.getImages() != null) {
            imageService.uploadFile(freeBoardCreateDto.getImages(), freeBoard);
        }
        return FreeBoardReadDto.toDto(freeBoard);
    }

    @Override
    public void deleteBoard(Long id) {
        freeBoardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public FreeBoardReadDto editBoard(Long id, FreeBoardCreateDto freeBoardDto, User user) throws IOException {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow();
        freeBoard.setFreeTitle(freeBoardDto.getFreeTitle());
        freeBoard.setFreeContent(freeBoardDto.getFreeContent());
        freeBoard.setUser(user);
        freeBoard = freeBoardRepository.save(freeBoard);

        if(freeBoardDto.getImages() != null) {
            imageService.uploadFile(freeBoardDto.getImages(), freeBoard);
        }
        return FreeBoardReadDto.toDto(freeBoard);
    }
}
