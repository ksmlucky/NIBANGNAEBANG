package com.footprints.businessservice.app.domain.chat.entity;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class ChatRoom extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private String id;
    private String title;

    @ElementCollection
    @CollectionTable(name = "chat_room_member", joinColumns = @JoinColumn(name = "chat_room_id", referencedColumnName = "chat_room_id"))
    private List<ChatRoomMember> members;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessages;

    private Integer currentMemberCount;
    private Integer totalMemberCount;
    private LocalDateTime closingTime;
    private Integer fee;

    public static ChatRoom createChatRoom(String title, ChatRoomRequest request) {
        return ChatRoom.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .currentMemberCount(1)
                .totalMemberCount(request.getTotalMemberCount())
                .fee(request.getFee())
                .build();
    }

    public void addChatMessages(ChatMessage chatMessage) {
        this.chatMessages.add(chatMessage);
    }

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChatRoomMember {
        // userid
        private Long id;
        // nickname
        private String nickname;

    }
}