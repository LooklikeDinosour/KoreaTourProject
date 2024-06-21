package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.domain.Criteria;
import com.kotu.koreatourism.dto.BoardUpdateDTO;
import com.kotu.koreatourism.dto.PageDTO;

import java.util.List;

public interface BoardService {

    //글 작성하기
    public int createPost(Board board);

    //상세글 불러오기
    public Board findPost(int bid);

    //글 수정하기
    public void updatePost(int bid, BoardUpdateDTO updateParam);

    //글 제거하기
    public void deletePost(int bid);

    //카테고리별로 분류
    //boardtype이 다른데 어떻게 조회를 해오나.. class는 상속받아야하나?? 흠.. 잠시 보류
    public List<Board> findAllPost(String boardCategory, Criteria criteria);

    public int findTotalPost(String boardCategory);

    public PageDTO getPageDTO(String boardCategory, Criteria criteria);

    //내가 쓴글 조회
    public List<Board> findMyAllPost(String userId);


}
