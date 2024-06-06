package com.kotu.koreatourism.dto.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kotu.koreatourism.config.TourLocationBasedDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize(using = TourLocationBasedDeserializer.class)
public class TourLocationBasedItemDTO {

    @JsonProperty("item")
    private List<TourLocationBasedDTO> tourLocations;

    public TourLocationBasedItemDTO(List<TourLocationBasedDTO> tourLocations) {
        this.tourLocations = tourLocations;
    }



}
