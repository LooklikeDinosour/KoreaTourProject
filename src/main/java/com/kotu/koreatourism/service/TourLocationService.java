package com.kotu.koreatourism.service;

import com.kotu.koreatourism.dto.tour.TourAreaSigunguDTO;

import java.io.IOException;

public interface TourLocationService {

    //GPS 위치기반
    public String locationBasedAPI(String callBackUrl, String serviceKey, String dataType, String contentTypeId, String mapX, String mapY) throws IOException;
    public String detailCommonInfoAPI(String callBackUrl, String serviceKey, String dataType, int contentId) throws IOException;
    public String detailIntroAPI(String callBackUrl, String serviceKey, String dataType, int contentId, int contentType) throws IOException;

    //지역기반
    public String areaBasedAPI(String callBackUrl, String serviceKey, String dataType, int contentTypeId, TourAreaSigunguDTO areaSigunguCode) throws IOException;

}
