package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.TourPlace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface PlaceSaveMapper {

    public void savePlace(@Param("placeInfo")TourPlace placeInfo, @Param("userId") String userId);

    public void saveUserPlace(@Param("userId") String userId, @Param("placeId") int placeId);

    public List<TourPlace> findAllPlace(String userId);
}
