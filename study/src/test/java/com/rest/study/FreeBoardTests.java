package com.rest.study;

import com.rest.study.user.entity.User;
import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.board.freeboard.repository.FreeBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class FreeBoardTests {

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Test
    void testJpa() {
        FreeBoard q1 = new FreeBoard();
        q1.setFreeTitle("아이유 콘서트 보신 분?");
        q1.setFreeContent("너무 재밌어요,,,");

        User user = new User();
        user.setUserId("user1");

        q1.setUser(user);
        this.freeBoardRepository.save(q1);
    }
}
