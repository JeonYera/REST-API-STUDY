package com.rest.study.board.travelboard.repository;

import com.rest.study.board.travelboard.entity.TravelBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelBoardRepository extends JpaRepository<TravelBoard, Long> {


    List<TravelBoard> findAllByOrderByTravelIdDesc();
}
