package com.kotu.koreatourism.service;

import java.io.IOException;

public interface TourLocationService {

    //GPS 위치기반
    public String locationBasedAPI(String callBackUrl, String serviceKey, String dataType, String contentTypeId, String mapX, String mapY) throws IOException;


    public String detailCommonInfoAPI(String callBackUrl, String serviceKey, String dataType, int contentId) throws IOException;
    public String detailIntroAPI(String callBackUrl, String serviceKey, String dataType, int contentId) throws IOException;

    //지역기반
    public String areBasedAPI(String callBackUrl, String serviceKey, String dataType, int contentId) throws IOException;

}