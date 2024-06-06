package com.kotu.koreatourism.dto.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kotu.koreatourism.config.TourDetailCommonInfoDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize(using = TourDetailCommonInfoDeserializer.class)
public class TourDetailCommonItemDTO {

    @JsonProperty("item")
    private List<TourDetailCommonDTO> tourDetailInfoList;

    public TourDetailCommonItemDTO(List<TourDetailCommonDTO> tourDetail) {
        this.tourDetailInfoList = tourDetail;
    }
}
