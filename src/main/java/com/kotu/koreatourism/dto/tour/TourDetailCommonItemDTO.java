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
    private List<TourDetailCommonDTO> tourDetailCommonList;

    public TourDetailCommonItemDTO(List<TourDetailCommonDTO> tourDetailCommonList) {
        this.tourDetailCommonList = tourDetailCommonList;
    }
}
