package com.kotu.koreatourism.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TourDetailInfoDTO {

    @JsonProperty("contentid")
    private String contentId;

    @JsonProperty("contenttypeid")
    private String contentTypeId;

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
    private String areaCode;

    @JsonProperty("sigungucode")
    private String sigunguCode;

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
    private String zipcode;

    @JsonProperty("mapx")
    private String mapX;

    @JsonProperty("mapy")
    private String mapY;

    @JsonProperty("mlevel")
    private String mLevel;

    @JsonProperty("overview")
    private String overview;
}
