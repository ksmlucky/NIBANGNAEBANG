package com.footprints.businessservice.app.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDto {

    /**
     * 메시지 타입 : 입장(ENTER), 채팅(TALK), 퇴장(LEAVE)
     * 채팅(TALK) : 내용을 해당 채팅방을 구독하는 모든 클라이언트에게 전달
     */
    public enum MessageType {
        ENTER, TALK, LEAVE;
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String time;
}
