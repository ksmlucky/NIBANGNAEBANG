package com.footprints.businessservice.app.domain.chat.dto;

import com.footprints.businessservice.app.domain.chat.service.ChatService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoomDto {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDto(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessageDto chatMessageDto, ChatService chatService) {
        if (chatMessageDto.getType().equals(chatMessageDto.getType().ENTER)) {
            sessions.add(session);
            chatMessageDto.setMessage(chatMessageDto.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessageDto, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
