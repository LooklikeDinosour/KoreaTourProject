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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TourDetailIntroDeserializer extends JsonDeserializer {

    private final ObjectMapper objectMapper;

    public TourDetailIntroDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TourDetailIntroItemDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemNode = node.findValue("item");

        TourDetailIntroDTO[] tourDetailIntroArrays = objectMapper.treeToValue(itemNode, TourDetailIntroDTO[].class);
        List<TourDetailIntroDTO> tourDetailIntroList = Arrays.asList(tourDetailIntroArrays);
        return new TourDetailIntroItemDTO(tourDetailIntroList);
    }
}
