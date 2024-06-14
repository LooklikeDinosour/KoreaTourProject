package com.kotu.koreatourism.dto.tour;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TourDetailCommonDTO {

    @JsonProperty("contentid")
    private int contentId;

    @JsonProperty("contenttypeid")
    private int contentTypeId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("createdtime")
    private String createdTime;

    @JsonProperty("modifiedtime")
    private String modifiedTime;

    @JsonProperty("tel")
    private String tel;

    @JsonProperty("telname")
    private String telName;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("booktour")
    private String bookTour;

    @JsonProperty("firstimage")
    private String firstImage;

    @JsonProperty("firstimage2")
    private String firstImage2;

    @JsonProperty("cpyrhtdivcd")
    private String cpyrhtDivCd;

    @JsonProperty("areacode")
    private int areaCode;

    @JsonProperty("sigungucode")
    private int sigunguCode;

    @JsonProperty("cat1")
    private String cat1;

    @JsonProperty("cat2")
    private String cat2;

    @JsonProperty("cat3")
    private String cat3;

    @JsonProperty("addr1")
    private String addr1;

    @JsonProperty("addr2")
    private String addr2;

    @JsonProperty("zipcode")
    private int zipcode;

    @JsonProperty("mapx")
    private Double mapX;

    @JsonProperty("mapy")
    private Double mapY;

    @JsonProperty("mlevel")
    private String mLevel;

    @JsonProperty("overview")
    private String overview;
}
