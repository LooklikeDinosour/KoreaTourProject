package com.kotu.koreatourism.domain;

import java.sql.Timestamp;

public class Message {

    private int messageId; //Message PK
    private String sentUser; //보낸 회원
    private String receivedUser; //받은 회원
    private String title; // 쪽지 제목
    private Timestamp sentDate; // 보낸 날짜
    private Timestamp readDate; // 읽은 날짜
    private String sentReceivedIdentifier; // 수발신코드
    private int messageContentId; //message Content FK

}
