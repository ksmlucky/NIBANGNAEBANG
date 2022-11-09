package com.footprints.businessservice.app.domain.chat.entity;

import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;
    private String title;

    @ElementCollection
    @CollectionTable(name = "chat_room_member", joinColumns = @JoinColumn(name = "chat_room_id", referencedColumnName = "chat_room_id"))
    private List<ChatRoomMember> members;

    private Integer currentUserCount;
    private Integer totalUserCount;
    private LocalDateTime closingTime;
    private Integer fee;

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