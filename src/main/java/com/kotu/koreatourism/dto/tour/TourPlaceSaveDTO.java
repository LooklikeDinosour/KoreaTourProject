package com.kotu.koreatourism.dto.tour;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TourPlaceSaveDTO {

    private int placeId;
    @JsonProperty("addr1")
    private String addr1;
    @JsonProperty("addr2")
    private String addr2;
    @JsonProperty("areacode")
    private int areaCode;
    @JsonProperty("contentid")
    private int contentId;
    @JsonProperty("contenttypeid")
    private int contentTypeId;
    @JsonProperty("firstimage")
    private String firstImage;
    @JsonProperty("firstimage2")
    private String firstImage2;
    @JsonProperty("mapx")
    private double mapx;
    @JsonProperty("mapy")
    private double mapy;
    @JsonProperty("sigungucode")
    private int sigunguCode;
    @JsonProperty("tel")
    private String tel;
    @JsonProperty("title")
    private String title;
    @JsonProperty("zipcode")
    private int zipCode;
    @JsonProperty("overview")
    private String overview;

    @Override
    public String toString() {
        return "TourPlaceSaveDTO{" +
                "placeId=" + placeId +
                ", addr1='" + addr1 + '\'' +
                ", addr2='" + addr2 + '\'' +
                ", areaCode=" + areaCode +
                ", contentId=" + contentId +
                ", contentTypeId=" + contentTypeId +
                ", firstImage='" + firstImage + '\'' +
                ", firstImage2='" + firstImage2 + '\'' +
                ", mapx=" + mapx +
                ", mapy=" + mapy +
                ", sigunguCode=" + sigunguCode +
                ", tel='" + tel + '\'' +
                ", title='" + title + '\'' +
                ", zipCode=" + zipCode +
                ", overview='" + overview + '\'' +
                '}';
    }
}
