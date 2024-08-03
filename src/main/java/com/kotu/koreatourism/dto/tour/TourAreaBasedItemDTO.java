package com.kotu.koreatourism.dto.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kotu.koreatourism.config.TourAreaBasedDeserializer;
import com.kotu.koreatourism.config.TourLocationBasedDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize(using = TourAreaBasedDeserializer.class)
public class TourAreaBasedItemDTO {

    @JsonProperty("item")
    private List<TourAreaBasedDTO> tourLocations;

    @JsonProperty("totalCount")
    private int totalCount;

    public TourAreaBasedItemDTO(List<TourAreaBasedDTO> tourLocations, int totalCount) {
        this.tourLocations = tourLocations;
        this.totalCount = totalCount;
    }



}
