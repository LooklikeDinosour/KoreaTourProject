package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TourPlace;
import com.kotu.koreatourism.dto.tour.TourDetailCommonDTO;
import com.kotu.koreatourism.dto.tour.TourDetailCommonItemDTO;
import com.kotu.koreatourism.mapper.PlaceSaveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TourPlaceSaveServiceImpl implements TourPlaceSaveService{

    private final PlaceSaveMapper placeSaveMapper;
    @Override
    public Boolean savePlace(TourDetailCommonDTO placeCommonInfo, String userId) {

        TourPlace tourPlaceInfo = new TourPlace();
        tourPlaceInfo.setAddr1(placeCommonInfo.getAddr1());
        tourPlaceInfo.setAddr2(placeCommonInfo.getAddr2());
        tourPlaceInfo.setAreaCode(placeCommonInfo.getAreaCode());
        tourPlaceInfo.setContentId(placeCommonInfo.getContentId());
        tourPlaceInfo.setContentTypeId(placeCommonInfo.getContentTypeId());
        tourPlaceInfo.setFirstImage(placeCommonInfo.getFirstImage());
        tourPlaceInfo.setFirstImage2(placeCommonInfo.getFirstImage2());
        tourPlaceInfo.setMapX(placeCommonInfo.getMapX());
        tourPlaceInfo.setMapY(placeCommonInfo.getMapY());
        tourPlaceInfo.setSigunguCode(placeCommonInfo.getSigunguCode());
        tourPlaceInfo.setTel(placeCommonInfo.getTel());
        tourPlaceInfo.setTitle(placeCommonInfo.getTitle());
        tourPlaceInfo.setZipcode(placeCommonInfo.getZipcode());
        tourPlaceInfo.setOverview(placeCommonInfo.getOverview());

        return placeSaveMapper.savePlace(tourPlaceInfo, userId);
    }

    @Override
    public void deletePlace(int placeId) {

    }

    @Override
    public List<TourPlace> findAllPlace(String userId) {
        return null;
    }
}
