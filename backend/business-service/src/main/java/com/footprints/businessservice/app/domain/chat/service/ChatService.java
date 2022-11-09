package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import org.springframework.data.redis.listener.ChannelTopic;

import java.util.List;

public interface ChatService {
    List<ChatRoom> findAllRooms();
    ChatRoom findRoomById(String id);
    ChatRoom createChatRoom(ChatRoomRequest request);
    void enterChatRoom(String roomId);
    ChannelTopic getTopic(String roomId);
    List<ChatRoom> getMemberEnterRooms(String memberId);
    void deleteById(ChatRoom chatRoom);
}
