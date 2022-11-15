package com.footprints.businessservice.app.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom.ChatRoomMember;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
//@ApiModel("ChatRoomRequest")
public class ChatRoomReq {
//    @ApiModelProperty(name = "유저 1 닉네임", example = "하이")
//    String userNickname1;

//    @ApiModelProperty(name = "유저 2 닉네임", example = "폴리롤리")
//    String userNickname2;

//    @ApiModelProperty(name = "게시글 번호", example = "1")
//    Long boardNo;

    //    private String roomId;
    private String title;
    //    private String sender;
    private Integer totalMemberCount;
    private Integer fee;
    //    @JsonFormat(pattern="YYYY-MM-DD HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
//    private LocalDateTime closingTime;

//    private List<ChatRoomMember> members; // 채팅방의 멤버들
}
