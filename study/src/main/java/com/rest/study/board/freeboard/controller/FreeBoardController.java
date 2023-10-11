package com.rest.study.board.freeboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.study.board.foodboard.dto.FoodBoardReadDto;
import com.rest.study.board.freeboard.dto.FreeBoardReadDto;
import com.rest.study.user.entity.User;
import com.rest.study.user.service.UserService;
import com.rest.study.board.freeboard.dto.FreeBoardCreateDto;
import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.board.freeboard.service.FreeBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public ResponseEntity<FreeBoardReadDto> writeBoard(@ModelAttribute FreeBoardCreateDto freeBoardCreateDto) throws IOException {
        return ResponseEntity.ok(freeBoardService.writeBoard(freeBoardCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreeBoardReadDto> editBoard(
            @RequestParam("freeBoardDto") String freeBoardDtoStr,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @PathVariable("id") Long id) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        FreeBoardCreateDto freeBoardDto = objectMapper.readValue(freeBoardDtoStr, FreeBoardCreateDto.class);

        User user = userService.findByUserId(freeBoardDto.getFreeUserId());
        FreeBoardReadDto freeBoard = freeBoardService.editBoard(id, freeBoardDto, user, images);
        return ResponseEntity.ok(freeBoard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id){
        freeBoardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}