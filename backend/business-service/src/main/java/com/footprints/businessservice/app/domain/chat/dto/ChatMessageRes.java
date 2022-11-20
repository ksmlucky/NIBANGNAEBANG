package com.footprints.businessservice.app.domain.chat.dto;

import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ChatMessageRes {
    String id;

//    @ApiModelProperty(name = "메시지 전송 유저 닉네임", example = "하이")
    String senderNickname;

//    @ApiModelProperty(name = "메시지내용", example = "this is message content.....")
    String message;

//    @ApiModelProperty(name = "메시지 타입", example =  "TALK")
    ChatMessage.MessageType type;

//    @ApiModelProperty(name = "메시지 전송 시간", example = "2022-05-03 23:54:21")
    LocalDateTime createdAt;
}
