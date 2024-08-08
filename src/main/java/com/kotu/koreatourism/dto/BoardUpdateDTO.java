package com.kotu.koreatourism.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateDTO {

    @NotBlank(message = "제목을 입력하세요")
    private String title; // 제목
    @NotBlank(message = "내용을 입력하세요")
    private String content; // 작성내용
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //이거 없으면 update시 검증 실패하면 날짜 안보임
    private LocalDateTime regdate; // 객체에서 필요하다고 에러나서 추가
    private String area; // 지역
    private String boardCategory;

}
