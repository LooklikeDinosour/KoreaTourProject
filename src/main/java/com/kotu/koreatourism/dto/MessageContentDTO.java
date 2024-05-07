package com.kotu.koreatourism.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageContentDTO {

    private int messageId; //Message PK
    private String sentUser; //보낸 회원
    private String title; // 쪽지 제목
    private LocalDateTime sentDate; // 보낸 날짜
    private LocalDateTime readDate; // 받은 날짜
    private String sentReceivedIdentifier; //송수신 식별
    private int message_read_chk; // 읽었는지
    private int messageContentId; //PK
    private String messageContent; //메시지 내용
}
