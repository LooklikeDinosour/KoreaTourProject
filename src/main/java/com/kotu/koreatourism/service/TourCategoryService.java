package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.TourAreaCategory;
import com.kotu.koreatourism.dto.tour.TourAreaCategoryDTO;
import com.kotu.koreatourism.dto.tour.TourAreaSigunguDTO;

import java.util.List;

public interface TourCategoryService {

    //public List<TourCategoryDTO> takeCategoryList();

    public List<TourAreaCategoryDTO> takeCategory();

    public List<TourAreaCategoryDTO> takeCategoryChild(TourAreaCategoryDTO tourAreaCategoryDTO);

    public int takeApiCodeNum(String groupId);
}
