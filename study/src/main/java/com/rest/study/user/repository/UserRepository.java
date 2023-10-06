package com.rest.study.user.repository;

import com.rest.study.user.entity.User;
import com.rest.study.board.freeboard.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<FreeBoard, Long> {
    @Query("SELECT u FROM User u WHERE u.userId = ?1")
    User findByUserId(String freeUserId);
}
