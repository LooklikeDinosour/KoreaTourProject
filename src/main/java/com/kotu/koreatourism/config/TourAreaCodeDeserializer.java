package com.kotu.koreatourism.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotu.koreatourism.dto.tour.TourAreaCodeDTO;
import com.kotu.koreatourism.dto.tour.TourAreaCodeItemDTO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Slf4j
public class TourAreaCodeDeserializer extends JsonDeserializer {

    private final ObjectMapper objectMapper;

    public TourAreaCodeDeserializer() {
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public TourAreaCodeItemDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemNode = node.findValue("item");

            TourAreaCodeDTO[] tourAreaCodeArrays = objectMapper.treeToValue(itemNode, TourAreaCodeDTO[].class);
            List<TourAreaCodeDTO> tourAreaCodeList = Arrays.asList(tourAreaCodeArrays);
        //    log.info("배열 정렬 = {}", tourAreaCodeList);
            return new TourAreaCodeItemDTO(tourAreaCodeList);
    }
}

