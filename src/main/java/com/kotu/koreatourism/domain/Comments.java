package com.kotu.koreatourism.domain;

import java.sql.Timestamp;

public class Comments {

    private int commentId; //PK
    private String commentAuthor; //댓글 작성자
    private Timestamp commentDate; //댓글 작성일
    private String commentContent; //댓글 내용

}
