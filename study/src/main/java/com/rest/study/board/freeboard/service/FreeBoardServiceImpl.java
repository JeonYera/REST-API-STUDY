package com.rest.study.board.freeboard.service;

import com.rest.study.board.foodboard.dto.FoodBoardReadDto;
import com.rest.study.board.foodboard.entity.FoodBoard;
import com.rest.study.board.freeboard.dto.FreeBoardCreateDto;
import com.rest.study.board.freeboard.dto.FreeBoardReadDto;
import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.board.freeboard.repository.FreeBoardRepository;
import com.rest.study.image.freeImage.entity.FreeImageAttachment;
import com.rest.study.image.freeImage.service.FreeImageService;
import com.rest.study.user.entity.User;
import com.rest.study.user.repository.UserRepository;
import com.rest.study.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private FreeImageService imageService;

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
    public FreeBoardReadDto editBoard(Long id, FreeBoardCreateDto updateDto, User user, List<MultipartFile> images) throws IOException {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow();
        freeBoard.setFreeTitle(updateDto.getFreeTitle());
        freeBoard.setFreeContent(updateDto.getFreeContent());
        freeBoard.setUser(user);
        FreeBoard updatedFreeBoard = freeBoardRepository.save(freeBoard);

/*        if(freeBoardCreateDto.getImages() != null) {
            imageService.uploadFile(freeBoardCreateDto.getImages(), freeBoard);
        }

        freeBoard.setImages(uploadedImages);*/
        return FreeBoardReadDto.toDto(updatedFreeBoard);
    }
}
