package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Board;
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
        Board newPost = boardMapper.createPost(board);
    }

    @Override
    public void findPost(int bno) {

    }

    @Override
    public void updatePost(int bno, Board board) {

    }

    @Override
    public void deletePost(int bno) {

    }

    @Override
    public List<Board> findAllPost() {
        return null;
    }
}
