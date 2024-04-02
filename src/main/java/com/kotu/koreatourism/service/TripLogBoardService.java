package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TripLogBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripLogBoardService {

    private final TripLogBoard tlBoard;
}
