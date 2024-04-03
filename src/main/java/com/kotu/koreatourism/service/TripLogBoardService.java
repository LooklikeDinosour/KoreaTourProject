package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TripLogBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripLogBoardService implements boardService {

    private final TripLogBoard tlBoard;

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
