package com.footprints.businessservice.app.domain.chat.dto;

import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRoomRes {
    private String id;

    private String title;

    private List<ChatRoom.ChatRoomMember> members; // 채팅방의 멤버들

//    @ApiModelProperty(name = "최근 메시지")
    private ChatMessage chatMessage;

    private Integer currentMemberCount;

    private Integer totalMemberCount;

    public ChatRoomRes(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.title = chatRoom.getTitle();
        this.members = chatRoom.getMembers();
        this.chatMessage = chatRoom.getChatMessages().size() != 0 ? chatRoom.getChatMessages().get(chatRoom.getChatMessages().size() - 1) : new ChatMessage();
        this.currentMemberCount = chatRoom.getMembers().size();
        this.totalMemberCount = chatRoom.getTotalMemberCount();
    }
}
