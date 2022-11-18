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
public class ChatRoomReq {
    //    private String roomId;
    private String title;
    //    private String sender;
    private Integer totalMemberCount;
    private Integer fee;
}
