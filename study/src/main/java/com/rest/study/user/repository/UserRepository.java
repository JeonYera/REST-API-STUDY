package com.rest.study.user.repository;

import com.rest.study.user.entity.User;
import com.rest.study.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Board, Long> {
    @Query("SELECT u FROM User u WHERE u.userId = ?1")
    User findByUserId(String boardUserId);
}
