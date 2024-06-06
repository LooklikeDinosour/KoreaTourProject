package com.kotu.koreatourism.dto.tour;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TourLocationBasedDTO {

    @JsonProperty("addr1")
    private String addr1;
    @JsonProperty("addr2")
    private String addr2;
    @JsonProperty("areacode")
    private int areaCode;
    @JsonProperty("booktour")
    private String bookTour;
    @JsonProperty("cat1")
    private String cat1;
    @JsonProperty("cat2")
    private String cat2;
    @JsonProperty("cat3")
    private String cat3;
    @JsonProperty("contentid")
    private String contentId;
    @JsonProperty("contenttypeid")
    private String contentTypeId;
    @JsonProperty("createdtime")
    private String createdTime;
    @JsonProperty("dist")
    private String dist;
    @JsonProperty("firstimage")
    private String firstImage;
    @JsonProperty("firstimage2")
    private String firstImage2;
    @JsonProperty("cpyrhtDivCd")
    private String cpyrhtDivCd;
    @JsonProperty("mapx")
    private float mapx;
    @JsonProperty("mapy")
    private float mapy;
    @JsonProperty("mlevel")
    private int mlevel;
    @JsonProperty("modifiedtime")
    private String modifiedTime;
    @JsonProperty("sigungucode")
    private int sigunguCode;
    @JsonProperty("tel")
    private String tel;
    @JsonProperty("title")
    private String title;

    @Override
    public String toString() {
        return "TourLocationBasedDTO{" +
                "addr1='" + addr1 + '\'' +
                ", addr2='" + addr2 + '\'' +
                ", areaCode=" + areaCode +
                ", bookTour='" + bookTour + '\'' +
                ", cat1='" + cat1 + '\'' +
                ", cat2='" + cat2 + '\'' +
                ", cat3='" + cat3 + '\'' +
                ", contentId='" + contentId + '\'' +
                ", contentTypeId='" + contentTypeId + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", dist='" + dist + '\'' +
                ", firstImage='" + firstImage + '\'' +
                ", firstImage2='" + firstImage2 + '\'' +
                ", cpyrhtDivCd='" + cpyrhtDivCd + '\'' +
                ", mapx=" + mapx +
                ", mapy=" + mapy +
                ", mlevel=" + mlevel +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", sigunguCode=" + sigunguCode +
                ", tel='" + tel + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
