package com.rest.study.board.travelboard.service;

import com.rest.study.board.travelboard.entity.TravelBoard;
import com.rest.study.board.travelboard.repository.TravelBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TravelBoardServiceImpl implements TravelBoardService {

    @Autowired
    private TravelBoardRepository travelBoardRepository;

    @Override
    public List<TravelBoard> findAll() {
        return travelBoardRepository.findAllByOrderByTravelIdDesc();
    }

    @Override
    public TravelBoard findById(Long id) {
        return travelBoardRepository.findById(id).orElse(null); // null이면 null반환
    }

    @Override
    public TravelBoard save(TravelBoard travelBoard) {
        return travelBoardRepository.save(travelBoard);
    }

    @Override
    public void deleteById(Long id) {
        travelBoardRepository.deleteById(id);
    }
}
