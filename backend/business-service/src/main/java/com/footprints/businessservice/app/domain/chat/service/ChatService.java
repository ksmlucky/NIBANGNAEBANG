package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatService {
    List<ChatRoom> findAllRoom();
    ChatRoom findRoomById(String roomId);

    void createRoom(String memberId, ChatRoomRequest request);

    public <T> void sendMessage(WebSocketSession session, T message);
}
