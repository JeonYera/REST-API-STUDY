package com.rest.study.board.foodboard.service;

import com.rest.study.board.foodboard.dto.FoodBoardCreateDto;
import com.rest.study.board.foodboard.dto.FoodBoardReadDto;
import com.rest.study.board.foodboard.entity.FoodBoard;
import com.rest.study.user.entity.User;

import java.util.List;

public interface FoodBoardService {
    FoodBoardReadDto findBoard(Long id);

    FoodBoardReadDto writeBoard(FoodBoardCreateDto foodBoard);

    void deleteById(Long id);

    FoodBoardReadDto editBoard(Long id, FoodBoardCreateDto foodBoardDto, User user);
    List<FoodBoardReadDto> findBoards();
}
