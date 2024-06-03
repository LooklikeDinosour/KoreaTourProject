package com.kotu.koreatourism.service;

import java.io.IOException;

public interface TourLocationService {

    public String locationContentsTypeAPI(String callBackUrl, String serviceKey, String dataType, String contentTypeId) throws IOException;

    public String locationDetailInfoAPI(String callBackUrl, String serviceKey, String dataType, int contentId) throws IOException;

}
