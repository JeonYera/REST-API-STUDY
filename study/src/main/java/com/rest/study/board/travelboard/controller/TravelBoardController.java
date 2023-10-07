package com.rest.study.board.travelboard.controller;

import com.rest.study.board.travelboard.dto.TravelBoardDto;
import com.rest.study.board.travelboard.entity.TravelBoard;
import com.rest.study.board.travelboard.service.TravelBoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(origins = "http://localhost")
@RestController // @Controller인데 모든핸들러에 @ResponseBody어노테이션을 적용해준다.
@RequestMapping("/api/travelboards")
@Slf4j
public class TravelBoardController {

    @Autowired
    private TravelBoardService travelBoardService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(travelBoardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        TravelBoard travelBoard = travelBoardService.findById(id);
        if(travelBoard == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(travelBoard);
    }

    @PostMapping("/createBoard")
    public ResponseEntity<?> createTravelBoard(@Valid @RequestBody TravelBoardDto travelBoardDto) {
        TravelBoard travelBoard = travelBoardDto.toTravel();
        return ResponseEntity.ok(travelBoardService.save(travelBoard));
    }

    @PatchMapping("/updateBoard/{id}")
    public ResponseEntity<?> updateTravelBoard(@PathVariable Long id, @Valid @RequestBody TravelBoardDto travelBoardDto) {
        // 한건 조회
        TravelBoard travelBoard = travelBoardService.findById(id);
        if(travelBoard == null)
            return ResponseEntity.notFound().build();
        // 수정
        travelBoardDto.toTravel(travelBoard);
        travelBoardService.save(travelBoard);
        return ResponseEntity.ok(travelBoardService.findById(id));
    }

    @DeleteMapping("/deleteBoard/{id}")
    public ResponseEntity<?> deleteTravelBoard(@PathVariable Long id) {
        travelBoardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
