package com.footprints.businessservice.app.domain.chat.dto;

import com.footprints.businessservice.app.domain.chat.entity.ChatMessage.MessageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageDto {
    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private LocalDateTime createdAt; // 메세지보낸 시각
}
