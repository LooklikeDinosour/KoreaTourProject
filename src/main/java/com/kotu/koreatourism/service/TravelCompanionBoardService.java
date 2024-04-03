package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TravelCompanionBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelCompanionBoardService implements boardService{

    private final TravelCompanionBoard tcBoard;

    @Override
    public void registerPost() {

    }

    @Override
    public void updatePost() {

    }

    @Override
    public void deletePost() {

    }
}
