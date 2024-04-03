package com.kotu.koreatourism.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelCompanionBoard {

    private int travelCompanionId; // PK
    private String travelCompanionTitle; // 제목
    private Timestamp travelCompanionDate; // 작성날짜
    private String travelCompanionAuthor; // 작성자
    private String travelCompanionContent; // 작성내용
    private int travelCompanionCount; // 조회수
    private String travelCompanionArea; // 지역

}
