package com.kotu.koreatourism.service;


public interface TourDeserializerService {

    public <T> T parsingJsonObject(String json, Class<T> clazz);

//    public TourAreaCodeItemDTO parsingJsonAreaCode(String json);
//
//    public TourLocationBasedItemDTO parsingJsonLocation(String json);
}
