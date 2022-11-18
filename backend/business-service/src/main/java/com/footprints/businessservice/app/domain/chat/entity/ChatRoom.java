package com.footprints.businessservice.app.domain.chat.entity;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomReq;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
@ToString
@Document(collection = "chats")
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
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
//    private LocalDateTime closingTime; // 모집 마감 시간
    private Integer fee; // 총 구독료

    public static ChatRoom create(String memberId, String nickname, ChatRoomReq request) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.title = request.getTitle();
        chatRoom.currentMemberCount = 1;
        chatRoom.totalMemberCount = request.getTotalMemberCount();
        chatRoom.fee = request.getFee();



        List<ChatRoomMember> chatRoomMembers = new ArrayList<>();
        ChatRoomMember chatRoomMember = new ChatRoomMember();
        chatRoomMember.setId(Long.parseLong(memberId));
        chatRoomMember.setNickname(nickname); // 닉네임 받아와서 set 해주기
        chatRoomMembers.add(chatRoomMember);
        chatRoom.members = chatRoomMembers;


        chatRoom.chatMessages = new ArrayList<ChatMessage>();
        return chatRoom;
    }

    @Getter
    @Setter
    public static class ChatRoomMember {
        // userid
        private Long id;
        // nickname
        private String nickname;
        // 방장 여부
        private boolean isBangjang;
    }

    public void updateMember(List<ChatRoomMember> chatRoomMembers) {
        this.members = chatRoomMembers;
    }
}