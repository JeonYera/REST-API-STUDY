package com.rest.study.board.service;

import com.rest.study.board.dto.BoardCreateDto;
import com.rest.study.board.dto.BoardReadDto;
import com.rest.study.board.entity.Board;
import com.rest.study.user.entity.User;

import java.io.IOException;
import java.util.List;


public interface BoardService {
    List<Board> findAllByOrderByBoardIdDesc();

    BoardReadDto findBoard(Long id);

    BoardReadDto writeBoard(BoardCreateDto boardCreateDto) throws IOException;

    void deleteBoard(Long id);

    BoardReadDto editBoard(Long id, BoardCreateDto boardDto, User user) throws IOException;

}
