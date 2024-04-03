package com.kotu.koreatourism.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TripLogBoard {

    private int tripLogId; //PK
    private String tripLogTitle; //제목
    private Timestamp tripLogDate; //작성날짜
    private String tripLogAuthor; //작성자
    private String tripLogContent; //작성내용
    private String tripLogCount; //조회수


}
