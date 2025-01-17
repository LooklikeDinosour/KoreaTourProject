package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.domain.Criteria;
import com.kotu.koreatourism.dto.BoardUpdateDTO;
import com.kotu.koreatourism.dto.PageDTO;
import com.kotu.koreatourism.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    //private final TripLogBoard tlBoard;
    private final BoardMapper boardMapper;

    @Override
    public int createPost(Board board) {
        return boardMapper.createPost(board);
    }

    @Override
    public Board findPost(int bid) {
        return boardMapper.findPost(bid);
    }

    @Override
    public void updatePost(int bid, BoardUpdateDTO updateParam) {
        Board post = boardMapper.findPost(bid);
        post.setTitle(updateParam.getTitle());
        post.setContent(updateParam.getContent());
        post.setArea(updateParam.getArea());
        boardMapper.updatePost(bid, post);
    }
    @Override
    public void deletePost(int bid) {
        //삭제기능은 본인 글이 맞아야 하는 로직
        boardMapper.deletePost(bid);
    }

    @Override
    public List<Board> findAllPost(String boardCategory, Criteria criteria) {
       return boardMapper.findAllPost(boardCategory, criteria);
    }

    @Override
    public int findTotalPost(String boardCategory, Criteria criteria) {
        return boardMapper.findTotalPost(boardCategory, criteria);
    }

    @Override
    public PageDTO getPageDTO(String boardCategory, Criteria criteria) {
        int totalPost = findTotalPost(boardCategory, criteria);
        return new PageDTO(criteria, totalPost);
    }

    @Override
    public List<Board> findMyAllPost(String userId) {
        return boardMapper.findMyAllPost(userId);
    }
}
