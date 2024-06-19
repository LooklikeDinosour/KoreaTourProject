package com.kotu.koreatourism.service;

import com.kotu.koreatourism.domain.Message;
import com.kotu.koreatourism.domain.MessageContent;
import com.kotu.koreatourism.domain.SiteUser;
import com.kotu.koreatourism.dto.LoginDTO;
import com.kotu.koreatourism.dto.MessageContentDTO;
import com.kotu.koreatourism.mapper.MessageMapper;
import com.kotu.koreatourism.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService{

    private final UserService userService;

    private final UserMapper userMapper;

    private final MessageMapper messageMapper;

    private final SqlSession sqlSession;

    //메시지
    @Override
    @Transactional
    public void sendMessage(MessageContent messageContent, Message message) {

        //키값을 불러오기 위해서 기존과 다른 방식으로 진행
        Integer messageContentId = sendMessageContent(messageContent);
        log.info("메시지콘텐츠 PK 값 = {}", messageContentId);
        message.setMessageContentId(messageContentId);
        messageMapper.sendMessage(message);
    }
    private Integer sendMessageContent(MessageContent messageContent) {
        this.sqlSession.insert("sendMessageContent", messageContent);
        return messageContent.getMessageContentId();
    }

    //메시지 내용 찾기
    @Override
    public MessageContentDTO findContent(int messageId) {
        return messageMapper.findContent(messageId);
    }

    @Override
    public List<Message> findAllMessage(String type, String userId) {
        log.info("서비스단 type = {}, userId={}", type, userId);
        return messageMapper.findAllMessage(type, userId);
    }


    @Override
    public void deleteMessage(String sentReceivedIdentifier, int messageId) {
        messageMapper.deleteMessage(sentReceivedIdentifier, messageId);
    }
    @Override
    public void readMessage(int messageContentId) {
        String currentUserName = userService.getCurrentUserName();
        messageMapper.readMessage(messageContentId, currentUserName);
    }
}
