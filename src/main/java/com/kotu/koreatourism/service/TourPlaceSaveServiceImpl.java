package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TourPlace;
import com.kotu.koreatourism.mapper.PlaceSaveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TourPlaceSaveServiceImpl implements TourPlaceSaveService{

    private final PlaceSaveMapper placeSaveMapper;
    @Override
    public Boolean savePlace(TourPlace placeInfo, String userId) {
        return placeSaveMapper.savePlace(placeInfo, userId);
    }

    @Override
    public void deletePlace(int placeId) {

    }

    @Override
    public List<TourPlace> findAllPlace(String userId) {
        return null;
    }
}
