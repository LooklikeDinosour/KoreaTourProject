package com.kotu.koreatourism.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kotu.koreatourism.config.TourAreaCodeDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize(using = TourAreaCodeDeserializer.class)
public class TourAreaCodesDTO {

    @JsonProperty("item")
    private List<TourAreaCodeDTO> tourCodes;

    public TourAreaCodesDTO(List<TourAreaCodeDTO> tourCodes) {
        this.tourCodes = tourCodes;
    }
}
