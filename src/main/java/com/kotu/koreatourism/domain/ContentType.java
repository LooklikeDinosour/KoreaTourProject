package com.kotu.koreatourism.domain;

public enum ContentType {
    ATTRACTION(12, "관광지"),
    CULTURAL_FACILITY(14, "문화시설"),
    EVENT_FESTIVAL(15, "행사/공연/축제"),
    TRAVEL_ROUTE(25, "여행코스"),
    LEISURE_SPORTS(28, "레저스포츠"),
    ACCOMMODATION(32, "숙박"),
    SHOPPING(38, "쇼핑"),
    RESTAURANT(39, "식당");

    private final int contentTypeId;
    private final String contentName;

    ContentType(int contentTypeId, String contentName) {
        this.contentTypeId = contentTypeId;
        this.contentName = contentName;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }

    public String getContentName() {
        return contentName;
    }

    public static ContentType fromUrlName(String contentName) {
        for(ContentType content : values()) {
            if (content.name().equalsIgnoreCase(contentName.replace("-","_"))) {
                return content;
            }
        }
        throw new IllegalArgumentException(contentName + "는 존재하지 않는 컨텐츠입니다.");
    }


}
