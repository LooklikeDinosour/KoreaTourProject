package com.kotu.koreatourism.service;

import com.kotu.koreatourism.dto.TourAreaCodesDTO;
import com.kotu.koreatourism.dto.TourLocationDTO;

public interface TourCodeService {

    public <T> T parsingJsonObject(String json, Class<T> clazz);

//    public TourAreaCodesDTO parsingJsonAreaCode(String json);
//
//    public TourLocationDTO parsingJsonLocation(String json);
}
