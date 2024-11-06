package com.kotu.koreatourism.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {

    private Long likeId;
    private String userId; // 좋아요 누른 유저
    private String contentId; // 좋아요 누른 API 컨텐츠
    private int likeCount; // 좋아요 수
    private LocalDateTime createdDate; // 좋아요 누른 일자

}
