package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TourAreaCategory;
import com.kotu.koreatourism.dto.tour.TourAreaCategoryDTO;
import com.kotu.koreatourism.dto.tour.TourAreaSigunguDTO;
import com.kotu.koreatourism.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourCategoryServiceImpl implements TourCategoryService {

    private final CategoryMapper categoryMapper;

//    @Override
//    public List<TourCategoryDTO> takeCategoryList() {
//        log.info("카테고리 리스트 = {}", categoryMapper.takeCategoryList());
//        return categoryMapper.takeCategoryList();
//    }

    @Override
    public List<TourAreaCategoryDTO> takeCategory() {
        return categoryMapper.takeCategory();
    }


    @Override
    public List<TourAreaCategoryDTO> takeCategoryChild(TourAreaCategoryDTO tourAreaCategoryDTO) {
        log.info("전달값 1 = {}, 전달값 2 = {}, 전달값 3 = {}",tourAreaCategoryDTO.getGroupId(), tourAreaCategoryDTO.getCategoryLv(), tourAreaCategoryDTO.getCategoryDetailOr());
        TourAreaCategory tourAreaCategory = new TourAreaCategory();
        tourAreaCategory.setGroupId(tourAreaCategoryDTO.getGroupId());
        tourAreaCategory.setCategoryLv(tourAreaCategoryDTO.getCategoryLv());
        tourAreaCategory.setCategoryDetailOr(tourAreaCategoryDTO.getCategoryDetailOr());
        return categoryMapper.takeCategoryChild(tourAreaCategory);
    }

    @Override
    public int takeApiCodeNum(String groupId) {
        return categoryMapper.takeApiCodeNum(groupId);
    }
}
