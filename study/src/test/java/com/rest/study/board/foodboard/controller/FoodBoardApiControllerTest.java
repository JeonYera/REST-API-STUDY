package com.rest.study.board.foodboard.controller;

import com.rest.study.board.foodboard.entity.FoodBoard;
import com.rest.study.board.foodboard.repository.FoodBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FoodBoardApiControllerTest {

    @Autowired
    private FoodBoardRepository foodBoardRepository;
    @Test
    void createBoard() {
        FoodBoard f1 = new FoodBoard();
        f1.setFoodTitle("두 어노테이션의 차이");
        f1.setFoodContent("그냥 냅다 해보면 됩니다.");
        //f1.setFoodMemberId("kimnana");
        f1.setFoodCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.foodBoardRepository.save(f1);
    }
}