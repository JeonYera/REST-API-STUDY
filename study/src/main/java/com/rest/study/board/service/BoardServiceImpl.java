package com.rest.study.board.service;

import com.rest.study.board.dto.BoardCreateDto;
import com.rest.study.board.dto.BoardReadDto;
import com.rest.study.board.entity.Board;
import com.rest.study.board.repository.BoardRepository;
import com.rest.study.image.entity.Image;
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
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

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

        if(boardCreateDto.getImages() != null && !boardCreateDto.getImages().isEmpty()) {
            List<Image> images = imageService.uploadFile(boardCreateDto.getImages(), board);
            board.setImages(images);
        }
        return BoardReadDto.toDto(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
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
            List<Image> images = imageService.uploadFile(multipartFiles, board);
            board.getImages().clear();
            board.getImages().addAll(images);
        }

        board = boardRepository.save(board);
        return BoardReadDto.toDto(board);
    }

}
