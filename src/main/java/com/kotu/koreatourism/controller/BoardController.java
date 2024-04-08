package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.domain.BoardDTO;
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
    @GetMapping("/tcboard")
    public String findAllBoard(Model model) {

        List<Board> allPosts = boardService.findAllPost();
        log.info("allposts = {}", allPosts);
        model.addAttribute("allPosts", allPosts);

        return "/boardList";
    }

    //게시글 작성하기 폼으로 이동
    @GetMapping("/createpost")
    public String createPostform() {

        return "/createPost";
    }
    //게시글 작성하기
    @PostMapping("/createpost")
    public String createPost(@ModelAttribute("Board") Board board, RedirectAttributes redirectAttributes) {
        log.info("board_category={}", board.getBoardCategory());
        board.setBoardCategory("tc");
        board.setArea("수원");
        Board createdPost = boardService.createPost(board);
        redirectAttributes.addAttribute("postbid", createdPost.getBid());

        return "redirect:/readpost/{postbid}";
    }
    //게시글 상세보기
    @GetMapping("/readpost/{postbid}")
    public String readPost(@PathVariable("postbid") int postId, Model model) {
        Board post = boardService.findPost(postId);
        log.info("post={}", post);
        model.addAttribute("post", post);
        return "/postdetail";
    }

    //게시글 수정하기
    @GetMapping("/{postbid}/update")
    public String updateForm(@PathVariable("postbid") int postbid, Model model) {

        Board post = boardService.findPost(postbid);
        model.addAttribute("post", post);
        return "/updatePost";
    }

    //게시글 수정하기
    @PostMapping("/{postbid}/update")
    public String updatePost(@PathVariable("postbid") int postbid, @ModelAttribute BoardDTO updateParam) {

        boardService.updatePost(postbid, updateParam);
        return "/updatePost";
    }
}
