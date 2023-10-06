package com.rest.study.board.travelboard.service;

import com.rest.study.board.travelboard.entity.TravelBoard;

import java.util.List;

public interface TravelBoardService {
    List<TravelBoard> findAll();

    TravelBoard findById(Long id);

    TravelBoard save(TravelBoard travelBoard);

    void deleteById(Long id);
}
