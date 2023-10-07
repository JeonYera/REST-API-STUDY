package com.rest.study.board.freeboard.repository;

import com.rest.study.board.freeboard.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    List<FreeBoard> findAllByOrderByFreeIdDesc();
}
