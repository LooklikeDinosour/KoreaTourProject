package com.kotu.koreatourism.dto.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kotu.koreatourism.config.TourDetailIntroDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonDeserialize(using = TourDetailIntroDeserializer.class)
public class TourDetailIntroItemDTO {

    @JsonProperty("item")
    private List<TourDetailIntroDTO> tourDetailIntroList;

    public TourDetailIntroItemDTO(List<TourDetailIntroDTO> tourDetailIntroList) {
        this.tourDetailIntroList = tourDetailIntroList;
    }
}
