package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.dto.BoardDTO;
import com.kotu.koreatourism.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController  {

    private final BoardService boardService;

    //게시판 조회하기
    @GetMapping("/{board_category}")
    public String findAllBoard(Model model, @PathVariable("board_category") String boardCategory) {

        log.info("board_category = {}", boardCategory);
        List<Board> allPosts = boardService.findAllPost(boardCategory);
        log.info("allposts = {}", allPosts);
        model.addAttribute("allPosts", allPosts);
        model.addAttribute("board_category", boardCategory);

        return "board/boardList";
    }

    //게시글 작성하기 폼으로 이동
    @GetMapping("/{board_category}/createpost")
    public String createPostform(@PathVariable("board_category") String board_category, Model model) {
        log.info("board_category:createPost(Get) = {}", board_category);
        model.addAttribute("board_category", board_category);
        return "board/createPost";
    }
    //게시글 작성하기
    @PostMapping("/{board_category}/createpost")
    public String createPost(@ModelAttribute("Board") Board board,
                             @RequestParam("board_category") String boardCategory,
                             RedirectAttributes redirectAttributes) {
        log.info("board_category,createPost(Post)={}", board.getBoardCategory());
        board.setBoardCategory(boardCategory);
        boardService.createPost(board);
        Board createdPost = boardService.findPost(board.getBid());
        redirectAttributes.addAttribute("postbid", createdPost.getBid());

        return "redirect:/board/readpost/{postbid}";
    }
    //게시글 상세보기
    @GetMapping("/readpost/{postbid}")
    public String readPost(@PathVariable("postbid") int postId, Model model) {
        Board post = boardService.findPost(postId);
        log.info("post={}", post);
        model.addAttribute("post", post);
        return "board/postdetail";
    }

    //게시글 수정하기
    @GetMapping("/{postbid}/update")
    public String updateForm(@PathVariable("postbid") int postbid, Model model) {

        Board post = boardService.findPost(postbid);
        model.addAttribute("post", post);
        return "board/updatePost";
    }

    //게시글 수정하기
    @PostMapping("/{postbid}/update")
    public String updatePost(@PathVariable("postbid") int postbid, @ModelAttribute BoardDTO updateParam) {

        boardService.updatePost(postbid, updateParam);
        return "redirect:/board/readpost/{postbid}";
    }

    //게시글 삭제
    @GetMapping("/{postbid}/delete")
    public String deletePost(@PathVariable("postbid") int postbid, RedirectAttributes redirectAttributes) {
        log.info("삭제 요청이 들어왔습니다.");
        Board loadedPost = boardService.findPost(postbid);
        String loadedPostCategory = loadedPost.getBoardCategory();
        if(loadedPost != null) {
            boardService.deletePost(postbid);
            redirectAttributes.addAttribute("board_category", loadedPostCategory);
            redirectAttributes.addFlashAttribute("msg", "삭제됐습니다.");
        }
        log.info("삭제 완료");
        return "redirect:/board/{board_category}";
    }
}
