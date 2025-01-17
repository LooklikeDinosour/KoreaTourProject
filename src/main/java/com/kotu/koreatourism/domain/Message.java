package com.kotu.koreatourism.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private int messageId; //Message PK
    private String sentUser; //보낸 회원
    private String receivedUser; //받은 회원
    private String title; // 쪽지 제목
    private LocalDateTime sentDate; // 보낸 날짜
    private LocalDateTime readDate; // 읽은 날짜
    private String sentReceivedIdentifier; // 수발신코드
    private int messageContentId; //message Content FK

    private int total; // 전체 메시지 수
}
