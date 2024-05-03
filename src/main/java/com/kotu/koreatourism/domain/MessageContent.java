package com.kotu.koreatourism.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageContent {

    private int messageContentId; //PK
    private String messageContent; //메시지 내용
}
