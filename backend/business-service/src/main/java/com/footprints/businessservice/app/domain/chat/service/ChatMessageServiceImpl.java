package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatMessageReq;
import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.repository.ChatMessageRepository;
import com.footprints.businessservice.app.domain.chat.repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service("ChatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /** 채팅 메시지 생성 **/
    @Override
    public ChatMessage registerChatMessage(ChatMessageReq chatMessageReq, String memberId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessageReq.getRoomId()).orElse(null);

        LocalDateTime localDateTime = LocalDateTime.now();

        ChatMessage chatMessage = ChatMessage.create(
                memberId,
                chatMessageReq.getMessage(),
                localDateTime,
                chatMessageReq.getType());

        Update update = new Update();
        update.push("chatMessages", chatMessage);
        Criteria criteria = Criteria.where("_id").is(chatMessageReq.getRoomId());
        mongoTemplate.updateFirst(Query.query(criteria), update, "chats");

        return chatMessage;
    }
}