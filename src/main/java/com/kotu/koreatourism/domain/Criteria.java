package com.kotu.koreatourism.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

    private int page; // 조회 페이지
    private int amount; // 한 페이지에 보여지는 데이터 갯수 ex) 10개, 20개, 50개, 100개 보기

    //검색
    private String searchType;
    private String searchKeyword;

    public Criteria() {
        this.page = 1;
        this.amount = 10;
        this.searchType = "title";
        this.searchKeyword= "";
    }

    // 기본 외에 조건에 따라 전달 받기 위한 생성자
    public Criteria(int page, int amount, String searchType, String searchKeyword) {
        this.page = page;
        this.amount = amount;
        this.searchType = searchType;
        this.searchKeyword = searchKeyword;
    }

    //페이지 시작 지정
    public int getPageStart() {
        return (page - 1) * amount;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", amount=" + amount +
                '}';
    }
}
