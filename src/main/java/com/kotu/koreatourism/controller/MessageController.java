package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Message;
import com.kotu.koreatourism.domain.MessageContent;
import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.MessageContentDTO;
import com.kotu.koreatourism.service.MessageService;
import com.kotu.koreatourism.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String messageBoard(Model model, Principal principal) {
        String userId = principal.getName();
        log.info("접속한 유저 아이디 가져오기 = {}", userId);
        List<Message> receivedMessages = messageService.findAllMessage("Received", userId);
        model.addAttribute("receivedMessages", receivedMessages);
        return "message/messageBoard";
    }

    @GetMapping("/createmessage")
    public String createMessage(Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("messageContent", new MessageContent());
        return "message/createMessageForm";
    }

    @PostMapping("/sendmessage")
    public String sendMessage(@ModelAttribute MessageContent messageContent,
                              @ModelAttribute Message message,
                              RedirectAttributes redirectAttributes) {
        log.info("message.sentUser ={}", message.getSentUser());
        log.info("message.receivedUser ={}", message.getReceivedUser());
        log.info("message.Identifier = {}", message.getSentReceivedIdentifier());
        log.info("messageContent.content ={}", messageContent.getMessageContent());


        //존재하는 유저인지 검증
        String receivedUser = message.getReceivedUser();
        boolean isUserInfo = userService.checkUserIdExist(receivedUser);
//        SiteUser userInfo = userMapper.findByUserId(receivedUser);
        log.info("쪽지 수신 유저 확인 = {}", isUserInfo);
        if(!isUserInfo) {
            redirectAttributes.addFlashAttribute("errorMessage", receivedUser + "는 없는 아이디입니다.");
            return "redirect:/message/createmessage";
        }
        messageService.sendMessage(messageContent, message);
        return "redirect:/message";
    }

    //받은 쪽지
    @GetMapping("/received")
    public String receivedMessages(Model model, Principal principal) {
        //String userId = session.getId();
        String userId = principal.getName();
        log.info("접속한 유저 아이디 가져오기 = {}", userId);
        List<Message> receivedMessages = messageService.findAllMessage("Received", userId);
        model.addAttribute("receivedMessages", receivedMessages);
        return "message/messageBoard";
    }

    //보낸 쪽지
    @GetMapping("/sent")
    public String sentMessages(Model model, Principal principal) {
        String userId = principal.getName();
        log.info("접속한 유저 아이디 가져오기 = {}", userId);
        List<Message> sentMessages = messageService.findAllMessage("Sent", userId);
        model.addAttribute("sentMessages", sentMessages);
        return "message/sentMessagesBoard";
    }

    //쪽지 내용
    @GetMapping("/detail/{messageId}")
    public String messageDetail(Model model,
                                @PathVariable("messageId") int messageId,
                                HttpSession httpSession) {
        MessageContentDTO findContent = messageService.findContent(messageId);

        log.info("쪽지 상세보기 = {}", findContent.toString());
        int messageContentId = findContent.getMessageContentId();
        messageService.readMessage(messageContentId);
        model.addAttribute("content", findContent);
        httpSession.setAttribute("replySender", findContent.getSentUser());
        return "message/messageDetail";
    }

    @GetMapping("/delete/{messageId}")
    public String deleteMessage(@PathVariable("messageId") int messageId) {
        MessageContentDTO content = messageService.findContent(messageId);
        String sentReceivedIdentifier = content.getSentReceivedIdentifier();
        log.info("송수신 식별자 = {}", sentReceivedIdentifier);
        messageService.deleteMessage(sentReceivedIdentifier, messageId);
        String lowerAddress = sentReceivedIdentifier.toLowerCase();
        return "redirect:/message/" + lowerAddress;
    }

    @GetMapping("/reply")
    public String replyMessage(HttpSession session, Model model) {
        String replySender = (String)session.getAttribute("replySender");
        MessageContent messageContent = new MessageContent();
        Message message = new Message();

        model.addAttribute("message", message);
        model.addAttribute("messageContent", messageContent);
        model.addAttribute("sender", replySender);

        return "message/replyMessageForm";
    }

//    히든타입으로 값불러와서 해결해보기

//    @GetMapping("/delete/{messageId}")
//    public String messageDelete(@PathVariable("messageId") int messageId, @ModelAttribute("content") MessageContentDTO mcDTO) {
//        String sentReceivedIdentifier = mcDTO.getSentReceivedIdentifier();
//        String title = mcDTO.getTitle();
//        log.info("제목 불러오니 = {}", title);
//        log.info("송수신 식별자 = {}", sentReceivedIdentifier);
//        //messageService.deleteMessage(messageId);
//        return "redirect:/";
//    }
}
