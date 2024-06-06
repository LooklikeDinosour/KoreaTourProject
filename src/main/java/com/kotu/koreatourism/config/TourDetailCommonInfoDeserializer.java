package com.kotu.koreatourism.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotu.koreatourism.dto.tour.TourDetailCommonDTO;
import com.kotu.koreatourism.dto.tour.TourDetailCommonItemDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TourDetailCommonInfoDeserializer extends JsonDeserializer {

    private final ObjectMapper objectMapper;

    public TourDetailCommonInfoDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TourDetailCommonItemDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    //    log.info("node 내부 구조? = {}", node.toString());
        JsonNode itemNode = node.findValue("item");

        TourDetailCommonDTO[] tourDetailCommonArrays = objectMapper.treeToValue(itemNode, TourDetailCommonDTO[].class);
        List<TourDetailCommonDTO> tourDetailCommonList = Arrays.asList(tourDetailCommonArrays);

        return new TourDetailCommonItemDTO(tourDetailCommonList);
    }

}
