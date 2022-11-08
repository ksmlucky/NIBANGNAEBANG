package com.footprints.businessservice.app.domain.chat.entity;

import com.footprints.businessservice.global.common.BaseEntity;

import javax.persistence.*;

@Entity
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
}
