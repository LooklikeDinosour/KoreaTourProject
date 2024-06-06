package com.kotu.koreatourism.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotu.koreatourism.dto.tour.TourDetailIntroDTO;
import com.kotu.koreatourism.dto.tour.TourDetailIntroItemDTO;
import com.kotu.koreatourism.dto.tour.TourLocationBasedItemDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class TourDetailIntroDeserializer extends JsonDeserializer {

    private final ObjectMapper objectMapper;

    public TourDetailIntroDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TourDetailIntroItemDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemNode = node.findValue("item");

        //상세설명이 없는 경우가 있어서 null
        if(itemNode == null) {
            log.warn("TourDetailIntroItemDTO itemNode is null.");
            return new TourDetailIntroItemDTO(Collections.emptyList());
        }

        TourDetailIntroDTO[] tourDetailIntroArrays = objectMapper.treeToValue(itemNode, TourDetailIntroDTO[].class);
        List<TourDetailIntroDTO> tourDetailIntroList = Arrays.asList(tourDetailIntroArrays);
        return new TourDetailIntroItemDTO(tourDetailIntroList);
    }
}
