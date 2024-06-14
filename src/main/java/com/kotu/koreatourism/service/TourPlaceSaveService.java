package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TourPlace;

import java.util.List;

public interface TourPlaceSaveService {

    public Boolean savePlace(TourPlace placeInfo, String userId);

    public void deletePlace(int placeId);

    public List<TourPlace> findAllPlace(String userId);
}
