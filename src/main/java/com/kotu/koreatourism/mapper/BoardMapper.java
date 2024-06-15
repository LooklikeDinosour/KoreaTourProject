package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.dto.BoardUpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    //글 작성하기
    public int createPost(Board board);

    //상세글 불러오기
    public Board findPost(int bid);

    //글 수정하기
    public void updatePost(@Param("bid") int bid,
                           @Param("updateParam") Board updateParam);

    //글 제거하기
    public int deletePost(int bid);

    //카테고리 별로 글 분류하기
    public List<Board> findAllPost(String boardCategory);

    //내가 쓴 글 조회
    public List<Board> findMyAllPost(String userId);


}
