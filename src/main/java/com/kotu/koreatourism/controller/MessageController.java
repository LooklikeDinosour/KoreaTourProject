package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Message;
import com.kotu.koreatourism.domain.MessageContent;
import com.kotu.koreatourism.service.MessageService;
import com.kotu.koreatourism.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/received")
    public String receivedMessages(Model model, Principal principal) {
        //String userId = session.getId();
        String userId = principal.getName();
        log.info("접속한 유저 아이디 가져오기 = {}", userId);
        List<Message> receivedMessages = messageService.findAllMessage("Received", userId);
        model.addAttribute("receivedMessages", receivedMessages);
        return "message/messageBoard";
    }
}
