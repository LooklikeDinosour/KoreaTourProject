package com.kotu.koreatourism.controller;


import com.kotu.koreatourism.dto.CommentDTO;
import com.kotu.koreatourism.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
}
