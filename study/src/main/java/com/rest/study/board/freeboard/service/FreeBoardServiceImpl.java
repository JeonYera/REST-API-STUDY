package com.rest.study.board.freeboard.service;

import com.rest.study.board.freeboard.dto.FreeBoardDto;
import com.rest.study.board.freeboard.dto.FreeBoardReadDto;
import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.board.freeboard.repository.FreeBoardRepository;
import com.rest.study.image.freeImage.entity.FreeImageAttachment;
import com.rest.study.image.freeImage.service.FreeImageService;
import com.rest.study.user.entity.User;
import com.rest.study.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FreeBoardServiceImpl implements FreeBoardService{

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private UserRepository userRepository;

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
    public FreeBoard save(FreeBoardDto freeBoardDto, List<MultipartFile> images) throws IOException {
        User user = userRepository.findByUserId(freeBoardDto.getFreeUserId());
        FreeBoard freeBoard = freeBoardDto.toFreeBoardDto(user, null);
        freeBoard = freeBoardRepository.save(freeBoard); // 먼저 FreeBoard 객체를 저장

        List<FreeImageAttachment> uploadedImages = new ArrayList<>();
        if (images != null) {
            for (MultipartFile file : images) {
                FreeImageAttachment image = (FreeImageAttachment) imageService.uploadFile(file, freeBoard);
                uploadedImages.add(image);
            }
        }

        freeBoard.setImages(uploadedImages);
        return freeBoardRepository.save(freeBoard); // 업데이트된 FreeBoard 객체를 다시 저장
    }

    @Override
    public void deleteBoard(Long id) {
        freeBoardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public FreeBoardReadDto editBoard(Long id, FreeBoardDto updateDto, User user) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow();
        freeBoard.setFreeTitle(updateDto.getFreeTitle());
        freeBoard.setFreeContent(updateDto.getFreeContent());
        freeBoard.setUser(user);
        FreeBoard updatedFreeBoard = freeBoardRepository.save(freeBoard);
        return FreeBoardReadDto.toDto(updatedFreeBoard);
    }
}
