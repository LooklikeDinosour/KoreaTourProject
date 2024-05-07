package com.kotu.koreatourism.mapper;

import com.kotu.koreatourism.domain.Message;
import com.kotu.koreatourism.domain.MessageContent;
import com.kotu.koreatourism.dto.MessageContentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

    //쪽지 보내기

    public int sendMessageContent(MessageContent messageContent);
    public int sendMessage(Message message);

    //public Message findMessage(int mid);
    //쪽지 내용 불러오기
    public MessageContentDTO findContent(int messageId);

    //쪽지리스트 불러오기 (받은, 보낸편지함. where Send, Received)
    public List<Message> findAllMessage(@Param("type") String type,
                                        @Param("userId") String userId);
//    public List<Message> findReceivedMessage();
//    public List<Message> findSentMessage();

    //쪽지삭제 쪽지내용이 1개인데 처음 삭제는 막고, 두번째 삭제에서 삭제 시켜야 한다.
    public void deleteMessage(@Param("type") String type,
                              @Param("messageId") int messageId);

    public void readMessage(int messageContentId);

}
