package com.kotu.koreatourism.domain;

import lombok.Getter;

@Getter
public class Criteria {

    private int page; // 조회 페이지
    private int amount; // 한 페이지에 보여지는 데이터 갯수 ex) 10개, 20개, 50개, 100개 보기

    public Criteria() {
        this.page = 1;
        this.amount = 10;
    }

    // 기본 외에 조건에 따라 전달 받기 위한 생성자
    public Criteria(int page, int amount) {
        this.page = page;
        this.amount = amount;
    }

    //페이지 시작 지정
    public int getPageStart() {
        return (page - 1) * amount;
    }
}
