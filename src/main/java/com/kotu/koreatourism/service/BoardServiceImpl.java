package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.domain.BoardDTO;
import com.kotu.koreatourism.domain.TripLogBoard;
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
    public void createPost(Board board) {
       int newPost = boardMapper.createPost(board);
    }

    @Override
    public void findPost(int bid) {

    }

    @Override
    public void updatePost(int bid, BoardDTO updateParam) {
        boardMapper.updatePost(bid, updateParam);
    }

    @Override
    public void deletePost(int bid) {
        //삭제기능은 본인 글이 맞아야 하는 로직
        boardMapper.deletePost(bid);
    }

    @Override
    public List<Board> findAllPost() {
        return null;
    }
}
