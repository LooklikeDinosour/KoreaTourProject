package com.kotu.koreatourism.service;


public interface TourCodeService {

    public <T> T parsingJsonObject(String json, Class<T> clazz);

//    public TourAreaCodesDTO parsingJsonAreaCode(String json);
//
//    public TourLocationDTO parsingJsonLocation(String json);
}
