package com.kotu.koreatourism;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.mapper.BoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTest {

//    @Autowired
//    BoardMapper boardMapper;
//
//    @DisplayName("게시글 작성테스트")
//    @Test
//    public void createPostTest() {
//        //given
//        Board board = new Board();
//        board.setBoardCategory("tl");
//        board.setTitle("디스이즈 여행기록 테스트 제목");
//        board.setAuthor("테스트1");
//        board.setContent("수원여행기 짱이다. 내용");
//        board.setArea("수원");
//        int testPost = boardMapper.createPost(board);
//        //then
//
//        //when
//        assertThat(testPost).isNotNull();
//    }

//    @DisplayName("게시글 삭제 테스트")
//    @Test
//    public void deletePostTest() {
//        //given
//           int dbid = boardMapper.deletePost(2);
//        //then
//
//        //when
//        assertThat(dbid == 1).isTrue();
//    }
}
