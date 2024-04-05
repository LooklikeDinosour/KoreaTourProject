package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //글 작성하기
    public int createPost(Board board);

    //상세글 불러오기
    public void findPost(int bid);

    //글 수정하기
    public void updatePost(int bid, Board board);

    //글 제거하기
    public int deletePost(int bid);

    //글 조회하기
    //boardtype이 다른데 어떻게 조회를 해오나.. class는 상속받아야하나?? 흠.. 잠시 보류
    public List<Board> findAllPost();


}
