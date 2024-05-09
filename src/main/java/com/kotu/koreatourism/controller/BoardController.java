package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.dto.BoardUpdateDTO;
import com.kotu.koreatourism.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        Board board = new Board();
        model.addAttribute("post", board);
        return "board/createPost";
    }
    //게시글 작성하기
    @PostMapping("/{board_category}/createpost")
    public String createPost(@Validated @ModelAttribute("post") Board board,
                             BindingResult bindingResult,
                             @PathVariable("board_category") String board_category,
                             RedirectAttributes redirectAttributes) {

        log.info("보드 카테고리 = {}", board.getBoardCategory());
        log.info("보드 카테고리 = {}", board.getAuthor());
        log.info("보드 카테고리 = {}", board.getTitle());

        if(bindingResult.hasErrors()) {
            log.info("게시글 작성 에러 = {}", bindingResult);
            return "board/createPost";
        }

        boardService.createPost(board);
        Board createdPost = boardService.findPost(board.getBid());
        redirectAttributes.addAttribute("postbid", createdPost.getBid());

        return "redirect:/board/readpost/{postbid}";
    }
    //게시글 상세보기
    @GetMapping("/readpost/{postbid}")
    public String readPost(@PathVariable("postbid") int postId, Model model) {

        Board post = boardService.findPost(postId);
        log.info("readPost={}", post);
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
    public String updatePost(@PathVariable("postbid") int postbid,
                             @Validated @ModelAttribute("post") BoardUpdateDTO updateParam,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("게시글 수정하기 errors = {}", bindingResult);
            return "board/updatePost";
        }

        log.info("updateParam.title ={}", updateParam.getTitle());
        boardService.updatePost(postbid, updateParam);
        return "redirect:/board/readpost/{postbid}";
    }

    //게시글 삭제
    @GetMapping("/{postbid}/delete")
    public String deletePost(@PathVariable("postbid") int postbid, RedirectAttributes redirectAttributes) {

        log.info("삭제 요청이 들어왔습니다.");
        //삭제 전에 조회해서 테이블 카테고리만 가져오기
        Board loadedPost = boardService.findPost(postbid);
        String loadedPostCategory = loadedPost.getBoardCategory();

        //삭제
        boardService.deletePost(postbid);

        //해당 게시판으로 리다이렉트하기 위해서 카테고리값 넣어주기
        redirectAttributes.addAttribute("board_category", loadedPostCategory);
        redirectAttributes.addFlashAttribute("msg", "삭제됐습니다.");
        log.info("삭제 완료");

        return "redirect:/board/{board_category}";
    }
}
