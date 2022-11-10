package com.footprints.businessservice.app.domain.chat.entity;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Slf4j
@ToString
@Document(collation = "chat")
public class ChatRoom {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "chat_room_id")
    private String id;
    private String title; // 채팅방 제목(멜론, 넷플릭스 등)

//    @ElementCollection
//    @CollectionTable(name = "chat_room_member", joinColumns = @JoinColumn(name = "chat_room_id", referencedColumnName = "chat_room_id"))
    private List<ChatRoomMember> members; // 채팅방의 멤버들

//    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessages; // 채팅방의 메세지들

    private Integer currentMemberCount; // 현재 멤버 수
    private Integer totalMemberCount; // 전체 멤버 수
    @DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
    private LocalDateTime closingTime; // 모집 마감 시간
    private Integer fee; // 총 구독료

    public static ChatRoom create(String title, ChatRoomRequest request) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.title = title;
        chatRoom.currentMemberCount = 1;
        chatRoom.totalMemberCount = request.getTotalMemberCount();
        chatRoom.closingTime = request.getClosingTime();
        chatRoom.fee = request.getFee();
        chatRoom.members = new ArrayList<ChatRoomMember>();
        chatRoom.chatMessages = new ArrayList<ChatMessage>();
        return chatRoom;
    }

    public static class ChatRoomMember {
        // userid
        private Long id;
        // nickname
        private String nickname;

    }
}