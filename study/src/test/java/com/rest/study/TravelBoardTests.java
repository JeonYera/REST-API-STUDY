package com.rest.study;


import com.rest.study.board.travelboard.entity.TravelBoard;
import com.rest.study.board.travelboard.repository.TravelBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class TravelBoardTests {

    @Autowired
    private TravelBoardRepository travelBoardRepository;

    @Test
    void testJpa() {
        TravelBoard t1 = new TravelBoard();
        t1.setTravelTitle("여행게시판 제목");
        t1.setTravelContent("여행게시판 내용 1.한국 2.일본 3.미국");
        t1.setTravelMemberId("king");
        t1.setTravelCreatedAt(LocalDateTime.now());
        this.travelBoardRepository.save(t1);
    }
}
