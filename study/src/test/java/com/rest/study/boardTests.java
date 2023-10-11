package com.rest.study;

import com.rest.study.user.entity.User;
import com.rest.study.board.entity.Board;
import com.rest.study.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class boardTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void testJpa() {
        Board q1 = new Board();
        q1.setBoardTitle("아이유 콘서트 보신 분?");
        q1.setBoardContent("너무 재밌어요,,,");

        User user = new User();
        user.setUserId("user1");

        q1.setUser(user);
        this.boardRepository.save(q1);
    }
}
