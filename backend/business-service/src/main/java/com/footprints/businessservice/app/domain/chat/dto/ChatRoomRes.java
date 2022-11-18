package com.footprints.businessservice.app.domain.chat.dto;

import com.footprints.businessservice.app.domain.board.reply.entity.Reply;
import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@Builder
//@ApiModel("ChatRoomResponse")
public class ChatRoomRes {
//    @ApiModelProperty(name = "채팅방 아이디", example = "62713ccfc7a1ef5b14c5fb15")
    private String id;

    private String title;

    private List<ChatRoom.ChatRoomMember> members; // 채팅방의 멤버들

//    @ApiModelProperty(name = "유저 1 닉네임", example = "하이")
//    String userNickname1;

//    @ApiModelProperty(name = "유저 2 닉네임", example = "폴리롤리")
//    String userNickname2;

//    @ApiModelProperty(name = "유저 2 이메일", example = "폴리롤리")
//    String userImage1;

//    @ApiModelProperty(name = "유저 1 프로필", example = "")
//    String userImage2;

//    @ApiModelProperty(name = "게시글 번호", example = "1")
//    Long boardNo;

//    @ApiModelProperty(name = "채팅 메시지")
//    List<ChatMessageRes> chatMessages;

//    @ApiModelProperty(name = "최근 메시지")
    private ChatMessage chatMessage;

//    @ApiModelProperty(name = "식재료 목록")
//    List<String> ingredientList;

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
