package com.kotu.koreatourism.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotu.koreatourism.dto.tour.TourLocationBasedItemDTO;
import com.kotu.koreatourism.dto.tour.TourLocationBasedDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
public class TourLocationBasedDeserializer extends JsonDeserializer {
    private final ObjectMapper objectMapper;

    private TourLocationBasedDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TourLocationBasedItemDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemNode = node.findValue("item");

        // "item" 노드가 없는 경우 null 반환
        // 지역에 따라 데이터가 없는 경우 Json 구조가 다름
        if (itemNode == null) {
            log.warn("itemNode is null. Returning empty TourLocationBasedItemDTO.");
            return new TourLocationBasedItemDTO(Collections.emptyList());
        }


        //지역정보
            TourLocationBasedDTO[] tourLocationBasedArray = objectMapper.treeToValue(itemNode, TourLocationBasedDTO[].class);
            List<TourLocationBasedDTO> tourLocationBasedList = Arrays.asList(tourLocationBasedArray);
            log.info("배열 정렬 = {}", tourLocationBasedList);
            return new TourLocationBasedItemDTO(tourLocationBasedList);
    }
}

