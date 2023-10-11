package com.rest.study.board.repository;

import com.rest.study.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    List<FreeBoard> findAllByOrderByFreeIdDesc();
}
