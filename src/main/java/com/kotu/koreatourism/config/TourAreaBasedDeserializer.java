package com.kotu.koreatourism.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotu.koreatourism.domain.ContentType;
import com.kotu.koreatourism.dto.tour.TourAreaBasedDTO;
import com.kotu.koreatourism.dto.tour.TourAreaBasedItemDTO;
import com.kotu.koreatourism.dto.tour.TourLocationBasedDTO;
import com.kotu.koreatourism.dto.tour.TourLocationBasedItemDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
public class TourAreaBasedDeserializer extends JsonDeserializer {
    private final ObjectMapper objectMapper;

    private TourAreaBasedDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TourAreaBasedItemDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemNode = node.findValue("item");

        // "item" 노드가 없는 경우 null 반환
        // 지역에 따라 데이터가 없는 경우 Json 구조가 다름
        if (itemNode == null) {
            log.warn("TourLocationBasedItemDTO itemNode is null.");
            return new TourAreaBasedItemDTO(Collections.emptyList());
        }


        //지역정보
            TourAreaBasedDTO[] tourLocationBasedArray = objectMapper.treeToValue(itemNode, TourAreaBasedDTO[].class);
            List<TourAreaBasedDTO> tourLocationBasedList = Arrays.asList(tourLocationBasedArray);
            log.info("배열 정렬 = {}", tourLocationBasedList);

            //contentTypeId 숫자 -> content로 변경
        // ex) 12 -> attraction
//        for (TourAreaBasedDTO tlbDTO : tourLocationBasedList) {
//            int contentTypeIdNum = Integer.parseInt(tlbDTO.getContentTypeId());
//            ContentType content = ContentType.contentTypeIdToContentType(contentTypeIdNum);
//            String contentName = String.valueOf(content).replace("_","-").toLowerCase();
//            log.info("content타입 문자로 변경 = {}", contentName);
//            tlbDTO.setContentTypeId(contentName);
//        }

            log.info("content type 변경후  = {}", tourLocationBasedList.toString());

            return new TourAreaBasedItemDTO(tourLocationBasedList);
    }
}

