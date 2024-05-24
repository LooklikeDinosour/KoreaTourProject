package com.kotu.koreatourism.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kotu.koreatourism.config.TourLocationDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize(using = TourLocationDeserializer.class)
public class TourLocationDTO {

    @JsonProperty("item")
    private List<TouristDTO> tourLocations;

    public TourLocationDTO(List<TouristDTO> tourLocations) {
        this.tourLocations = tourLocations;
    }
}
