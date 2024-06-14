package com.kotu.koreatourism.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourPlace {

    private int placeId;
    private String addr1;
    private String addr2;
    private int areaCode;
    private int contentId;
    private int contentTypeId;
    private String firstImage;
    private String firstImage2;
    private double mapX;
    private double mapY;
    private int sigunguCode;
    private String tel;
    private String title;
    private int zipcode;
    private String overview;
}
