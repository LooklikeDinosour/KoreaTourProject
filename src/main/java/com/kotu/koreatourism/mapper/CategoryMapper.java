package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.TourAreaCategory;
import com.kotu.koreatourism.dto.tour.TourAreaCategoryDTO;
import com.kotu.koreatourism.dto.tour.TourAreaSigunguDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //public List<TourCategoryDTO> takeCategoryList();

    public List<TourAreaCategoryDTO> takeCategory();

    public List<TourAreaCategoryDTO> takeCategoryChild(TourAreaCategory tourAreaCategory);

    public int takeApiCodeNum(String groupId);
}
