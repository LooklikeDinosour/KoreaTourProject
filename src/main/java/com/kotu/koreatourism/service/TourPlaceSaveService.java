package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TourPlace;
import com.kotu.koreatourism.dto.tour.TourDetailCommonDTO;
import com.kotu.koreatourism.dto.tour.TourDetailCommonItemDTO;

import java.util.List;

public interface TourPlaceSaveService {

    public Boolean savePlace(TourDetailCommonDTO placeCommonInfo, String userId);

    public void deletePlace(int placeId);

    public List<TourPlace> findAllPlace(String userId);
}
