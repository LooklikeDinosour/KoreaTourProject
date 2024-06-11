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

    public TourAreaBasedItemDTO(List<TourAreaBasedDTO> tourLocations) {
        this.tourLocations = tourLocations;
    }



}
