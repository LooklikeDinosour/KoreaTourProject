package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final UserService userService;

    @GetMapping("")
    public String messageBoard() {

        return "message/messageBoard";
    }

    @GetMapping("/createmessage")
    public String createMessage () {

        return "message/createMessageForm";
    }

    @PostMapping("/sendmessage")
    public String sendMessage() {
        return "redirect:/message/messageBoard";
    }
}
