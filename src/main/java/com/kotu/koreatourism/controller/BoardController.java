package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController  {

    private final BoardService boardService;

    @GetMapping("/travelcompanionboard")
    public String findAllBoard() {

        boardService.findAllPost();

        return "/boardList";
    }

    @GetMapping("/readBoard")
    public String readPost() {

        return "/readBoard";
    }
}
