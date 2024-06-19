package com.kotu.koreatourism.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private int bid; // PK
    @NotNull
    private String boardCategory; // 게시판 구분 tl 여행, tc 동행
    @NotBlank(message = "제목을 입력하세요!")
    private String title; // 제목
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate; // 작성날짜
    private String area; // 지역
    private String author; // 작성자
    @NotBlank(message = "내용을 입력하세요!")
    private String content; // 작성내용
    private int viewcount; // 조회수
    private String userId; // 유저아이디


}
