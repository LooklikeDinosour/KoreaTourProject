package com.kotu.koreatourism.domain;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private int commentId; //PK
    private String author; //댓글 작성자
    private LocalDateTime regdate; //댓글 작성일
    private String comment; //댓글 내용
    private int bid; // 게시글 연걸 FK
    private String userId;

}
