package com.kotu.koreatourism.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
public class BoardDTO {

    private String title; // 제목
    private String content; // 작성내용
    private String area; // 지역
    private LocalDateTime updatedate; // 수정날짜

}
