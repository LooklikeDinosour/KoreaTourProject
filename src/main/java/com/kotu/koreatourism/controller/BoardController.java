package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Board;
import com.kotu.koreatourism.domain.Criteria;
import com.kotu.koreatourism.domain.Message;
import com.kotu.koreatourism.domain.MessageContent;
import com.kotu.koreatourism.dto.BoardUpdateDTO;
import com.kotu.koreatourism.dto.CommentDTO;
import com.kotu.koreatourism.dto.PageDTO;
import com.kotu.koreatourism.service.BoardService;
import com.kotu.koreatourism.service.CommentService;
import com.kotu.koreatourism.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController  {

    private final BoardService boardService;
    private final CommentService commentService;

    //게시판 조회하기
    @GetMapping("/{board_category}")
    public String findAllBoard(Model model, Criteria criteria, @PathVariable("board_category") String boardCategory) {

        log.info("Received Criteria: page = {}, amount = {}, searchType = {}, searchKeyword = {}",
                criteria.getPage(), criteria.getAmount(), criteria.getSearchType(), criteria.getSearchKeyword());

        PageDTO pageDTO = boardService.getPageDTO(boardCategory, criteria);
        log.info("페이지DTO 현재 페이지 = {}, 페이지당 게시글 숫자 = {}", pageDTO.getCriteria().getPage(), pageDTO.getAmount());
        List<Board> allPosts = boardService.findAllPost(boardCategory, criteria);
        log.info("allPosts = {}", allPosts);

        log.info("PageDTO 계산 값 출력 = {}", pageDTO.toString());

        model.addAttribute("allPosts", allPosts);
        model.addAttribute("board_category", boardCategory);
        model.addAttribute("pageDTO", pageDTO);
        model.addAttribute("criteria", criteria);

        return "board/boardList";
    }

    //게시글 작성하기 폼으로 이동
    @GetMapping("/{board_category}/createpost")
    public String createPostForm(@PathVariable("board_category") String board_category, Model model) {
        log.info("board_category:createPost(Get) = {}", board_category);
        Board board = new Board();
        model.addAttribute("post", board);
        return "board/createPost";
    }
    //게시글 작성하기
    @PostMapping("/{board_category}/createpost")
    public String createPost(@Validated @ModelAttribute("post") Board board,
                             BindingResult bindingResult,
                             @PathVariable("board_category") String boardCategory,
                             RedirectAttributes redirectAttributes) {

        log.info("보드 카테고리 = {}", board.getBoardCategory());
        log.info("보드 카테고리 = {}", board.getAuthor());
        log.info("보드 카테고리 = {}", board.getTitle());
        log.info("보드 카테고리 = {}", board.getUserId());


        if(bindingResult.hasErrors()) {
            log.info("게시글 작성 에러 = {}", bindingResult);
            return "board/createPost";
        }

        boardService.createPost(board);
        Board createdPost = boardService.findPost(board.getBid());
        redirectAttributes.addAttribute("postbid", createdPost.getBid());

        return "redirect:/board/" + boardCategory + "/readpost/{postbid}";
    }
    //게시글 상세보기
    @GetMapping("{board_category}/readpost/{postbid}")
    public String readPost(@PathVariable("postbid") int postId,
                           @PathVariable("board_category") String boardCategory,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        Board post = boardService.findPost(postId);
        if(post == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "해당 게시글을 찾을 수 없습니다.");
            return "redirect:/board/" + boardCategory; //전체 게시판으로 돌아가는.. 카테고리별로 돌아가야되는데
        }
        //댓글 불러오기
        List<CommentDTO> allCommentList = commentService.findAllComment(postId);
        log.info("댓글 불러오기 = {}", allCommentList.toString());

        log.info("readPost={}", post);
        model.addAttribute("formType", "save");
        model.addAttribute("message", new Message());
        model.addAttribute("messageContent", new MessageContent());
        model.addAttribute("post", post);
        model.addAttribute("comments", allCommentList);
        return "board/postdetail";
    }

    //게시글 수정하기
    @GetMapping("{board_category}/{postbid}/update")
    public String updateForm(@PathVariable("postbid") int postbid,
                             @PathVariable("board_category") String boardCategory,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        Board post = boardService.findPost(postbid);

        if(post == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "해당 게시글을 찾을 수 없습니다.");
            return "redirect:/board/" + boardCategory; //전체 게시판으로 돌아가는.. 카테고리별로 돌아가야되는데
        }
        model.addAttribute("post", post);
        return "board/updatePost";
    }

    //게시글 수정하기
    @PostMapping("{board_category}/{postbid}/update")
    public String updatePost(@PathVariable("postbid") int postbid,
                             @PathVariable("board_category") String boardCategory,
                             @Validated @ModelAttribute("post") BoardUpdateDTO updateParam,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("게시글 수정하기 errors = {}", bindingResult);
            return "board/updatePost";
        }

        log.info("updateParam.title ={}", updateParam.getTitle());
        boardService.updatePost(postbid, updateParam);
        return "redirect:/board/" + boardCategory + "/readpost/{postbid}";
    }

    //게시글 삭제
    @GetMapping("{board_category}/{postbid}/delete")
    public String deletePost(@PathVariable("postbid") int postbid,
                             @PathVariable("board_category") String boardCategory,
                             RedirectAttributes redirectAttributes) {

        log.info("삭제 요청 게시글 ID: {}, 게시판 카테고리: {}", postbid, boardCategory);

        //삭제
        boardService.deletePost(postbid);

        //해당 게시판으로 리다이렉트하기 위해서 카테고리값 넣어주기
        redirectAttributes.addFlashAttribute("msg", "삭제됐습니다.");
        log.info("삭제 완료 게시글 ID: {}, 게시판 카테고리: {}", postbid, boardCategory);

        return "redirect:/board/"+boardCategory;
    }
}
