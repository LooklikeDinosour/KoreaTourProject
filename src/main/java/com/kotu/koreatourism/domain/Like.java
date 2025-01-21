package com.kotu.koreatourism.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Like {

    private Long likeId;
    private String userId; // 좋아요 누른 유저
    private int contentId; // 좋아요 누른 API 컨텐츠
    private int likeCount; // 좋아요 수
    private LocalDateTime createdDate; // 좋아요 누른 일자

}
