package com.footprints.businessservice.app.domain.chat.dto;

import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import lombok.Data;

@Data
public class ChatMessageDto {

    private String roomId;
    private String sender;
    private String message;
    private ChatMessage.MessageType type;
}
