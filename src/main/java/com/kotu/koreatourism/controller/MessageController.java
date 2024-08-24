package com.kotu.koreatourism.controller;

import com.kotu.koreatourism.domain.Criteria;
import com.kotu.koreatourism.domain.Message;
import com.kotu.koreatourism.domain.MessageContent;
import com.kotu.koreatourism.dto.MessageContentDTO;
import com.kotu.koreatourism.dto.PageDTO;
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

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;

    //받은 쪽지
    @GetMapping("/{message-status}")
    public String receivedMessages(Model model, Principal principal, Criteria criteria,
                                   @PathVariable("message-status") String messageStatus) {

        String userId = principal.getName();
        log.info("접속한 유저 아이디 가져오기 = {}, 메시지 송수신 = {}", userId, messageStatus);
        //대문자 기능은 배포 때 없애는게 편함
        PageDTO pageDTO = messageService.getPageDTO(userId, criteria, messageStatus);
        model.addAttribute("pageDTO", pageDTO);
        model.addAttribute("criteria", criteria);
        model.addAttribute("messageStatus", messageStatus);
        if (messageStatus.equals("received")) {
            List<Message> receivedMessages = messageService.findAllMessage(changeFirstLetterToUpper(messageStatus), userId, criteria);
            log.info("대문자 = {}", changeFirstLetterToUpper(messageStatus));
            model.addAttribute("receivedMessages", receivedMessages);
            return "message/messageboard";
        }
        List<Message> sentMessages = messageService.findAllMessage(changeFirstLetterToUpper(messageStatus), userId, criteria);
        log.info("대문자 = {}", changeFirstLetterToUpper(messageStatus));
        model.addAttribute("sentMessages", sentMessages);
        return "message/sentMessagesBoard";
    }

    private static String changeFirstLetterToUpper(String messageStatus) {
        if (messageStatus == null || messageStatus.isEmpty()) {
            return messageStatus;
        }
        return messageStatus.substring(0, 1).toUpperCase() + messageStatus.substring(1);
    }

    //보낸 쪽지
//    @GetMapping("/sent")
//    public String sentMessages(Model model, Principal principal, Criteria criteria) {
//        String userId = principal.getName();
//        log.info("접속한 유저 아이디 가져오기 = {}", userId);
//        List<Message> sentMessages = messageService.findAllMessage("Sent", userId, criteria);
//////        PageDTO pageDTO = messageService.getPageDTO(userId, criteria,);
////        model.addAttribute("sentMessages", sentMessages);
////        model.addAttribute("pageDTO", pageDTO);
////        model.addAttribute("criteria", criteria);
//        return "message/sentMessagesBoard";
//    }

//    @GetMapping("")
//    public String messageBoard(Model model, Criteria criteria, Principal principal) {
//        String userId = principal.getName();
//        log.info("접속한 유저 아이디 가져오기 = {}", userId);
//        List<Message> receivedMessages = messageService.findAllMessage("Received", userId, criteria);
//        PageDTO pageDTO = messageService.getPageDTO(userId, criteria);
//        model.addAttribute("receivedMessages", receivedMessages);
//        model.addAttribute("pageDTO", pageDTO);
//        model.addAttribute("criteria", criteria);
//        return "message/messageboard";
//    }
    @GetMapping("/messagewindows")
    public String createMessageWindow(@RequestParam(required = true) String receiver, Model model) {
        model.addAttribute("receiver", receiver);
        model.addAttribute("message", new Message());
        model.addAttribute("messageContent", new MessageContent());
        return "message/createMessageWindowsForm";
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
        redirectAttributes.addFlashAttribute("successMessage", "쪽지 발송에 성공했습니다.");
        return "redirect:/message";
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
