package com.kotu.koreatourism.service;

import com.kotu.koreatourism.dto.LikeDTO;
import com.kotu.koreatourism.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper likeMapper;

    public boolean toggleLike(String userId, int contentId) {
        //좋아요 상태 확인
        LikeDTO isLiked = likeMapper.findLikeByUser(userId, contentId);
        if(isLiked == null) {
            //좋아요

        }
        return true;
    }


}
