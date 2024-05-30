package com.kotu.koreatourism.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kotu.koreatourism.config.TourDetailInfoDeserializer;
import com.kotu.koreatourism.config.TourLocationDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize(using = TourDetailInfoDeserializer.class)
public class TourDetailInfoItemDTO {

    @JsonProperty("item")
    private List<TourDetailInfoDTO> tourDetailInfoList;

    public TourDetailInfoItemDTO(List<TourDetailInfoDTO> tourDetail) {
        this.tourDetailInfoList = tourDetail;
    }
}
