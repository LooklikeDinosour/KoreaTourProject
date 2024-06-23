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
                              CommentDTO commentDTO,
                              Principal principal) {
        log.info("boardCat={}", boardCategory);
        log.info("게시글 pk={}", bid);

        log.info("댓글 내용 = {}", commentDTO.toString());
        commentService.saveComment(commentDTO);

        return "redirect:/board/"+ boardCategory +"/readpost/" + bid;
    }


}
