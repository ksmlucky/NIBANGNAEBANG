package com.footprints.businessservice.app.domain.chat.entity;

import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class ChatMessage extends BaseEntity {
    public enum MessageType {
        ENTER, TALK, QUIT;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Long id;
    private MessageType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    private String sender;
    private String message;

    public static ChatMessage createChatMessage(ChatRoom chatRoom, String sender, String message, MessageType type) {
        return ChatMessage.builder()
                .chatRoom(chatRoom)
                .sender(sender)
                .message(message)
                .type(type)
                .build();
    }

    public void updateSender(String sender) {
        this.sender = sender;
    }

    public void updateMessage(String message) {
        this.message = message;
    }
}