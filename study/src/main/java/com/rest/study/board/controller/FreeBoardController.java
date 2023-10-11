package com.rest.study.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.study.board.dto.FreeBoardReadDto;
import com.rest.study.board.service.FreeBoardService;
import com.rest.study.user.entity.User;
import com.rest.study.user.service.UserService;
import com.rest.study.board.dto.FreeBoardCreateDto;
import com.rest.study.board.entity.FreeBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RequestMapping("/api/freeboards")
@RestController
@Slf4j
public class FreeBoardController {

    @Autowired
    private FreeBoardService freeBoardService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<FreeBoard>> getBoards() {
        List<FreeBoard> freeBoard = freeBoardService.findAllByOrderByFreeIdDesc();
        return ResponseEntity.ok(freeBoard);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreeBoardReadDto> getBoard(@PathVariable Long id) {
        FreeBoardReadDto freeBoard = freeBoardService.findBoard(id);
        if(freeBoard == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(freeBoard);
    }
    @PostMapping("/{write}")
    public ResponseEntity<FreeBoardReadDto> writeBoard(@Valid @ModelAttribute FreeBoardCreateDto freeBoardCreateDto) throws IOException {
        return ResponseEntity.ok(freeBoardService.writeBoard(freeBoardCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreeBoardReadDto> editBoard(
            @Valid @ModelAttribute FreeBoardCreateDto freeBoardDto,
            @PathVariable("id") Long id) throws IOException {

        User user = userService.findByUserId(freeBoardDto.getFreeUserId());
        return ResponseEntity.ok(freeBoardService.editBoard(id, freeBoardDto, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id){
        freeBoardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}