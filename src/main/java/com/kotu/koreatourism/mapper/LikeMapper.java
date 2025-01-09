package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

    //좋아요 상태 조회
    LikeDTO findLikeByUser(@Param("userId") String userId, @Param("contentId") int contentId);

    //좋아요 +1

    //좋아요 삭제
}
