package com.kotu.koreatourism;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.mapper.BoardMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    public void createPostTest() {
        //given
        Board board = new Board();
        board.setBoardCategory("tl");
        board.setTitle("디스이즈 여행기록 테스트 제목");
        board.setAuthor("테스트1");
        board.setContent("수원여행기 짱이다. 내용");
        board.setArea("수원");
        Board testPost = boardMapper.createPost(board);
        //then



        //when
        Assertions.assertThat(testPost.getBid() != 0).isTrue();
    }
}
