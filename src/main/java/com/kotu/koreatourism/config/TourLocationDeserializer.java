package com.kotu.koreatourism.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotu.koreatourism.dto.TourAreaCodeDTO;
import com.kotu.koreatourism.dto.TourAreaCodesDTO;
import com.kotu.koreatourism.dto.TourLocationDTO;
import com.kotu.koreatourism.dto.TouristDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
public class TourLocationDeserializer extends JsonDeserializer {
    private final ObjectMapper objectMapper;

    private TourLocationDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TourLocationDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemNode = node.findValue("item");

        // "item" 노드가 없는 경우 null 반환
        // 지역에 따라 데이터가 없는 경우 Json 구조가 다름
        if (itemNode == null) {
            log.warn("itemNode is null. Returning empty TourLocationDTO.");
            return new TourLocationDTO(Collections.emptyList());
        }


        //지역정보
            TouristDTO[] tourLocationsArray = objectMapper.treeToValue(itemNode, TouristDTO[].class);
            List<TouristDTO> tourlocationList = Arrays.asList(tourLocationsArray);
        //    log.info("배열 정렬 = {}", tourlocationList);
            return new TourLocationDTO(tourlocationList);
    }
}

