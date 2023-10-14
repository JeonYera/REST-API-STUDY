package com.rest.study.board.service;

import com.rest.study.S3.repository.FileRepository;
import com.rest.study.S3.service.FileService;
import com.rest.study.S3.service.FileServiceImpl;
import com.rest.study.board.dto.BoardCreateDto;
import com.rest.study.board.dto.BoardReadDto;
import com.rest.study.board.entity.Board;
import com.rest.study.board.repository.BoardRepository;
import com.rest.study.S3.entity.Image;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public List<Board> findAllByOrderByBoardIdDesc() {
        return boardRepository.findAllByOrderByBoardIdDesc();
    }

    @Override
    public BoardReadDto findBoard(Long id) {
        return BoardReadDto.toDto(boardRepository.findById(id).orElse(null));
    }

    @Override
    public BoardReadDto writeBoard(BoardCreateDto boardCreateDto) throws IOException {
        User user = userService.findByUserId(boardCreateDto.getBoardUserId());

        Board board = Board.builder()
                .boardContent(boardCreateDto.getBoardContent())
                .boardTitle(boardCreateDto.getBoardTitle())
                .user(user)
                .boardCreatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        board = boardRepository.save(board);

        List<Image> images = new ArrayList<>();
        if(boardCreateDto.getImages() != null && !boardCreateDto.getImages().isEmpty()) {
            for(MultipartFile file : boardCreateDto.getImages()) {
                String imageUrl = fileService.uploadFile(file);

                Image image = new Image();

                image.setOriginName(file.getOriginalFilename());
                image.setS3Url(imageUrl);
                image.setBoard(board);
                image.setImageFileSize(file.getSize());
                images.add(image);
            }
            images = fileRepository.saveAll(images);
            board.setImages(images);
        }
        return BoardReadDto.toDto(board);
    }

    @Override
    @Transactional
    public BoardReadDto editBoard(Long id, BoardCreateDto boardDto, User user) throws IOException {
        Board board = boardRepository.findById(id).orElseThrow();

        board.setBoardTitle(boardDto.getBoardTitle());
        board.setBoardContent(boardDto.getBoardContent());
        board.setUser(user);

        List<MultipartFile> multipartFiles = boardDto.getImages();
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            List<Image> newImages = new ArrayList<>();

            // 기존 이미지 목록 삭제
            List<Image> existingImages = board.getImages();
            if(existingImages != null && !existingImages.isEmpty()) {
                board.getImages().clear(); // 컬렉션에서 기존 이미지 제거
                fileRepository.deleteAll(existingImages); // 데이터베이스에서 기존 이미지 삭제
            }

            for(MultipartFile file : multipartFiles) {
                String imageUrl = fileService.uploadFile(file);

                Image image = new Image();
                image.setOriginName(file.getOriginalFilename());
                image.setS3Url(imageUrl);
                image.setBoard(board);
                image.setImageFileSize(file.getSize());
                newImages.add(image);
            }

            board.getImages().addAll(newImages);  // 새로운 이미지들을 컬렉션에 추가
            fileRepository.saveAll(newImages);    // 새로운 이미지들을 데이터베이스에 저장
        }

        board = boardRepository.save(board);
        return BoardReadDto.toDto(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
