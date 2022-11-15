package com.footprints.businessservice.app.domain.chat.entity;

import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ChatMessage {
    private static final long serialVersionUID = 6494678977089006639L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "chat_message_id")
    private String id;
    private String sender;
    private String message;

    @DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
    private LocalDateTime createdAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chat_room_id")
//    private ChatRoom chatRoom;

    private MessageType type;

    public enum MessageType {
        ENTER, TALK, QUIT;
    }

    public static ChatMessage create(String sender, String message, LocalDateTime createdAt, MessageType type) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.id = UUID.randomUUID().toString();
        chatMessage.sender = sender;
        chatMessage.message = message;
        chatMessage.createdAt = createdAt;
        chatMessage.type = type;
        return chatMessage;
    }
}