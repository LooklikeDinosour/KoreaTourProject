package com.kotu.koreatourism.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {

    private String title; // 제목
    private String author; // 작성자
    private String content; // 작성내용
    private String area; // 지역
    private LocalDateTime updatedate; // 수정날짜

}
