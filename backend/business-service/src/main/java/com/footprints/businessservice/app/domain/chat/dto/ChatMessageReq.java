package com.footprints.businessservice.app.domain.chat.dto;

import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
//@ApiModel("ChatMessageRequest")
public class ChatMessageReq {
//    @ApiModelProperty(name = "메시지 전송 유저 닉네임", example = "하이")
    String sender;          // 메시지 전송 유저 닉네임

//    @ApiModelProperty(name = "메시지가 속한 채팅방 Id", example = "")
    String roomId;      // 메시지가 속한 채팅방 아이디

//    @ApiModelProperty(name = "전송 메시지 내용", example = "this is message content....")
    String message;         // 메시지 내용

//    @ApiModelProperty(name = "메시지 타입", example =  "TALK")
    ChatMessage.MessageType type;

}
