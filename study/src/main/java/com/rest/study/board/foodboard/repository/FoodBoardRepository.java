package com.rest.study.board.foodboard.repository;

import com.rest.study.board.foodboard.entity.FoodBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface FoodBoardRepository extends JpaRepository<FoodBoard, Long> {
    List<FoodBoard> findAllByOrderByFoodIdDesc();

}
