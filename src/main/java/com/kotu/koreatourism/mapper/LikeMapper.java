package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.Like;
import com.kotu.koreatourism.domain.TourPlace;
import com.kotu.koreatourism.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeMapper {

    //좋아요 상태 조회
    Like findLikeByUser(@Param("userId") String userId, @Param("contentId") int contentId);

    //좋아요 +1(작동)
    int increaseLike(LikeDTO likeDTO);

    //좋아요 -1(취소)
    int decreaseLike(Long likeId);

    // 사용가 누른 모든 좋아요 리스트로 불러오기
    List<TourPlace> findAllLikesByUser(String userId);
}
