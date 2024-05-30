package com.kotu.koreatourism.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TourCodeServiceImpl implements TourCodeService {

    private final ObjectMapper objectMapper;

    public TourCodeServiceImpl() {
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public <T> T parsingJsonObject(String json, Class<T> clazz) {
        try {
            log.info("Json = {}, 파싱 데이터 = {}", json, objectMapper.readValue(json, clazz));
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
//
//    @Override
//    public TourAreaCodesDTO parsingJsonAreaCode(String json) {
//        try {
//            log.info("Json = {}", json);
//            log.info("파싱 데이터 = {}", objectMapper.readValue(json, TourAreaCodesDTO.class));
//            return objectMapper.readValue(json, TourAreaCodesDTO.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public TourLocationDTO parsingJsonLocation(String json) {
//        try {
//            log.info("Json = {}", json);
//            log.info("파싱 데이터 = {}", objectMapper.readValue(json, TourLocationDTO.class));
//            return objectMapper.readValue(json, TourLocationDTO.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
