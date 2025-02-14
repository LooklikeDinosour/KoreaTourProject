package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TourPlace;
import com.kotu.koreatourism.dto.tour.TourDetailCommonDTO;
import com.kotu.koreatourism.dto.tour.TourDetailCommonItemDTO;
import com.kotu.koreatourism.dto.tour.TourPlaceSaveDTO;
import com.kotu.koreatourism.mapper.PlaceSaveMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;



@Slf4j
@Service
@RequiredArgsConstructor
public class TourPlaceSaveServiceImpl implements TourPlaceSaveService{

    private final PlaceSaveMapper placeSaveMapper;

    @Override
    public TourPlace findContentId(int contentId) {
        log.info("테이블에 저장된 장소인지 확인절차");
        return placeSaveMapper.findContentId(contentId);
    }

    @Override
    @Transactional
    public void savePlace(TourDetailCommonDTO placeCommonInfo, String userId) {

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

        placeSaveMapper.savePlace(tourPlaceInfo, userId);
        placeSaveMapper.saveUserPlace(userId, tourPlaceInfo.getPlaceId());
    }

    @Override
    public void deletePlace(int placeId) {

    }

    @Override
    public List<TourPlaceSaveDTO> findAllPlace(String userId) {
        List<TourPlace> myPlaceList = placeSaveMapper.findAllPlace(userId);

        List<TourPlaceSaveDTO> myPlaceListToDtoList = myPlaceList.stream()
                .map(place -> {
                    TourPlaceSaveDTO tourPlaceSaveDTO = new TourPlaceSaveDTO();
                    tourPlaceSaveDTO.setAddr1(place.getAddr1());
                    tourPlaceSaveDTO.setAddr2(place.getAddr2());
                    tourPlaceSaveDTO.setAreaCode(place.getAreaCode());
                    tourPlaceSaveDTO.setContentId(place.getContentId());
                    tourPlaceSaveDTO.setContentTypeId(place.getContentTypeId());
                    tourPlaceSaveDTO.setFirstImage(place.getFirstImage());
                    tourPlaceSaveDTO.setFirstImage2(place.getFirstImage2());
                    tourPlaceSaveDTO.setMapx(place.getMapX());
                    tourPlaceSaveDTO.setMapy(place.getMapY());
                    tourPlaceSaveDTO.setSigunguCode(place.getSigunguCode());
                    tourPlaceSaveDTO.setTel(place.getTel());
                    tourPlaceSaveDTO.setTitle(place.getTitle());
                    tourPlaceSaveDTO.setZipCode(place.getZipcode());
                    tourPlaceSaveDTO.setOverview(place.getOverview());
                    return tourPlaceSaveDTO;
                })
                .collect(Collectors.toList());

        return myPlaceListToDtoList;
    }
}
