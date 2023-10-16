package com.rest.study.board.controller;

import com.rest.study.board.dto.BoardReadDto;
import com.rest.study.board.service.BoardService;
import com.rest.study.user.entity.User;
import com.rest.study.user.service.UserService;
import com.rest.study.board.dto.BoardCreateDto;
import com.rest.study.board.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

//@CrossOrigin(origins = "http://13.209.5.240")
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/boards")
@RestController
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Board>> getBoards() {
        List<Board> board = boardService.findAllByOrderByBoardIdDesc();
        return ResponseEntity.ok(board);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardReadDto> getBoard(@PathVariable Long id) {
        BoardReadDto board = boardService.findBoard(id);
        if(board == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(board);
    }
    @PostMapping
    public ResponseEntity<BoardReadDto> writeBoard(@Valid @ModelAttribute BoardCreateDto boardCreateDto) throws IOException {
        return ResponseEntity.ok(boardService.writeBoard(boardCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardReadDto> editBoard(
            @Valid @ModelAttribute BoardCreateDto boardDto,
            @PathVariable("id") Long id) throws IOException {

        User user = userService.findByUserId(boardDto.getBoardUserId());
        return ResponseEntity.ok(boardService.editBoard(id, boardDto, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id){
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}