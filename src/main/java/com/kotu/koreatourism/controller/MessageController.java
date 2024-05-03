package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Message;
import com.kotu.koreatourism.domain.MessageContent;
import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.service.MessageService;
import com.kotu.koreatourism.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("")
    public String messageBoard() {

        return "message/messageBoard";
    }

    @GetMapping("/createmessage")
    public String createMessage(Model model) {
        MessageContent messageContent = new MessageContent();
        Message message = new Message();

        model.addAttribute("message", message);
        model.addAttribute("messageContent", messageContent);

        return "message/createMessageForm";
    }

    @PostMapping("/sendmessage")
    public String sendMessage(@ModelAttribute MessageContent messageContent,
                              @ModelAttribute Message message) {
        log.info("message.sentUser ={}", message.getSentUser());
        log.info("message.receivedUser ={}", message.getReceivedUser());
        log.info("message.Identifier = {}", message.getSentReceivedIdentifier());
        log.info("messageContent.content ={}", messageContent.getMessageContent());
        messageService.sendMessage(messageContent, message);
        return "redirect:/message";
    }
}
