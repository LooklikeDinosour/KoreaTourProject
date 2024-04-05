package com.kotu.koreatourism.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {

    private int bid; // PK
    private String boardCategory; // 게시판 구분 tl 여행, tc 동행
    private String title; // 제목
    private LocalDateTime regdate; // 작성날짜
    private String area; // 지역
    private String author; // 작성자
    private String content; // 작성내용
    private int count; // 조회수


}
