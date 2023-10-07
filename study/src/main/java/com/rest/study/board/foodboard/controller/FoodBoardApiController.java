package com.rest.study.board.foodboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.study.board.foodboard.dto.FoodBoardCreateDto;
import com.rest.study.board.foodboard.dto.FoodBoardReadDto;
import com.rest.study.board.foodboard.entity.FoodBoard;
import com.rest.study.board.foodboard.service.FoodBoardService;
import com.rest.study.common.controller.StringUtils;
import com.rest.study.user.entity.User;
import com.rest.study.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;


@CrossOrigin(origins ="http://localhost")
@Slf4j
@RestController
@RequestMapping("/api/foodboards")
// @Controller 모든 핸들러에 @ResponseBody 어노테이션 적용해줌
// @ResponseBody 핸들러에 반환된 자바 객체를 Response Body에 써줌
public class FoodBoardApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private FoodBoardService foodBoardService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<FoodBoardReadDto>> getBoards() {
        return ResponseEntity.ok(foodBoardService.findBoards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodBoardReadDto> getBoard(@PathVariable Long id) {
        System.out.println(foodBoardService.findBoard(id));
        return ResponseEntity.ok(foodBoardService.findBoard(id));
    }

    // 이미지를 넣기 위해 form-data로 데이터를 받기 때문에 RequestBody 삭제
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FoodBoardReadDto> writeBoard(@Valid @ModelAttribute FoodBoardCreateDto foodBoardCreateDto) {
        return ResponseEntity.ok(foodBoardService.writeBoard(foodBoardCreateDto));
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FoodBoardReadDto> editBoard(@PathVariable Long id, @Valid @ModelAttribute FoodBoardCreateDto foodBoardDto) throws IllegalAccessException {
        User user = userService.findByUserId(foodBoardDto.getFoodUserId());
        return ResponseEntity.ok(foodBoardService.editBoard(id, foodBoardDto, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        foodBoardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}