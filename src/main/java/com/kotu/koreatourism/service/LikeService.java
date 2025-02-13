package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Like;
import com.kotu.koreatourism.dto.LikeDTO;
import com.kotu.koreatourism.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper likeMapper;

    public boolean toggleLike(String userId, int contentId) {
        //좋아요 상태 확인
        log.info("좋아요 상태 확인");
        Like liked = likeMapper.findLikeByUser(userId, contentId);
        LikeDTO isLiked = null;

        if (liked != null) {
           isLiked = convertToLikeDTO(liked);
        }

        if (isLiked == null) {
            //좋아요 적용
            log.info("좋아요 적용");
            LikeDTO newLike = new LikeDTO(null, userId, contentId, 1, LocalDateTime.now());
            likeMapper.increaseLike(newLike);
            return true;
        } else {
            if (isLiked.getLikeCount() > 0) {
                //좋아요 취소
                log.info("좋아요 취소");
                likeMapper.decreaseLike(isLiked.getLikeId());
                return false;
            }
        }
        //
        return false;
    }

    public List<LikeDTO> getLikeList(String userId) {
        return likeMapper.findAllLikesByUser(userId);
    }

    private LikeDTO convertToLikeDTO(Like liked) {
        return new LikeDTO(
                liked.getLikeId(),
                liked.getUserId(),
                liked.getContentId(),
                liked.getLikeCount(),
                liked.getCreatedDate()
        );
    }


}
