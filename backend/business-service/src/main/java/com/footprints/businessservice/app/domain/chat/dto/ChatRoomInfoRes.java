package com.footprints.businessservice.app.domain.chat.dto;

import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom.ChatRoomMember;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
//@ApiModel("ChatRoomInfoResponse")
public class ChatRoomInfoRes {
//    @ApiModelProperty(name = "채팅방 아이디", example = "62713ccfc7a1ef5b14c5fb15")
    String id;

    String title; // 채팅방 제목(멜론, 넷플릭스 등)

    List<ChatRoomMember> members; // 채팅방의 멤버들

//    @ApiModelProperty(name = "유저 1 이메일", example = "하이")
//    String userEmail1;

//    @ApiModelProperty(name = "유저 2 이메일", example = "폴리롤리")
//    String userEmail2;

//    @ApiModelProperty(name = "게시글 번호", example = "1")
//    Long boardNo;

//    @ApiModelProperty(name = "채팅 메시지")
    List<ChatMessageRes> chatMessages;

    Integer currentMemberCount; // 현재 멤버 수

    Integer totalMemberCount; // 전체 멤버 수

//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
//    LocalDateTime closingTime; // 모집 마감 시간

    Integer fee; // 총 구독료
}
