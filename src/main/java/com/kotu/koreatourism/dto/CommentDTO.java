package com.kotu.koreatourism.dto;

import java.sql.Timestamp;

public class CommentDTO {

    private int commentId; //PK
    private String author; //댓글 작성자
    private Timestamp regdate; //댓글 작성일
    private String comment; //댓글 내용
    private int bid; // 게시글 연걸 FK
    private String userId;

}