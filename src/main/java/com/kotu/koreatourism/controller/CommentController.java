package com.kotu.koreatourism.controller;


import com.kotu.koreatourism.dto.CommentDTO;
import com.kotu.koreatourism.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/{board_category}/readpost/{postbid}/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public String saveComment(@PathVariable("board_category") String boardCategory,
                              @PathVariable("postbid") int bid,
                              CommentDTO commentDTO) {
        log.info("boardCat={}", boardCategory);
        log.info("게시글 pk={}", bid);

        log.info("댓글 내용 = {}", commentDTO.toString());
        commentService.saveComment(commentDTO);

        return "redirect:/board/"+ boardCategory +"/readpost/" + bid;
    }

    @GetMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable("board_category") String boardCategory,
                                @PathVariable("commentId") int commentId,
                                @PathVariable("postbid") int bid) {
        log.info("boardCat={}", boardCategory);
        log.info("게시글 pk={}", bid);
        log.info("댓글 ID = {}", commentId);

        commentService.deleteComment(commentId, bid);

        return "redirect:/board/"+ boardCategory +"/readpost/" + bid;
    }
    @GetMapping("/{commentId}/update")
    public String callUpdateCommentForm(@PathVariable("board_category") String boardCategory,
                                        @PathVariable("commentId") int commentId,
                                        @PathVariable("postbid") int bid,
                                        Model model) {

        log.info("boardCat={}", boardCategory);
        log.info("게시글 pk={}", bid);
        log.info("댓글 ID = {}", commentId);

        CommentDTO findCommentDTO = commentService.findCommentById(commentId);
        log.info("댓글 찾아오기 = {}", findCommentDTO);
        model.addAttribute("commentDTO", findCommentDTO);
        model.addAttribute("postbid", bid);
        model.addAttribute("boardCategory", boardCategory);
        return "board/postdetail";
    }

    @PostMapping("/{commentId}/update")
    public ResponseEntity<CommentDTO> updateComment(@ModelAttribute CommentDTO commentDTO) {

        log.info("코멘트 수정 내용 = {}", commentDTO.getComment());
        return (commentService.updateComment(commentDTO) == 1) ? new ResponseEntity<>(commentDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
