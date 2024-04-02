package com.kotu.koreatourism.domain;

import java.sql.Timestamp;

public class TripLogBoard {

    private int tripLogId; //PK
    private String tripLogTitle; //제목
    private Timestamp tripLogDate; //작성날짜
    private String tripLogAuthor; //작성자
    private String tripLogContent; //작성내용
    private String tripLogCount; //조회수


}
