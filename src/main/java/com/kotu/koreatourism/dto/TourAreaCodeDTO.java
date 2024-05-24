package com.kotu.koreatourism.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TourAreaCodeDTO {

    @JsonProperty("code")
    private String code;

    @JsonProperty("rnum")
    private int rnum;

    @JsonProperty("name")
    private String name;

}
