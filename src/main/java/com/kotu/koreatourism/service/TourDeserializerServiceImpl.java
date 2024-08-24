package com.kotu.koreatourism.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TourDeserializerServiceImpl implements TourDeserializerService {

    private final ObjectMapper objectMapper;

    public TourDeserializerServiceImpl() {
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public <T> T parsingJsonObject(String json, Class<T> clazz) {
        try {
            log.info("Json 파싱 -> Java 객체 및 클래스 확인 = {}", clazz);
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
//
//    @Override
//    public TourAreaCodeItemDTO parsingJsonAreaCode(String json) {
//        try {
//            log.info("Json = {}", json);
//            log.info("파싱 데이터 = {}", objectMapper.readValue(json, TourAreaCodeItemDTO.class));
//            return objectMapper.readValue(json, TourAreaCodeItemDTO.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public TourLocationBasedItemDTO parsingJsonLocation(String json) {
//        try {
//            log.info("Json = {}", json);
//            log.info("파싱 데이터 = {}", objectMapper.readValue(json, TourLocationBasedItemDTO.class));
//            return objectMapper.readValue(json, TourLocationBasedItemDTO.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
