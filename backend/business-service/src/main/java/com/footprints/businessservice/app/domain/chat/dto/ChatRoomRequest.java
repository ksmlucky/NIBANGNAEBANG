package com.footprints.businessservice.app.domain.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatRoomRequest {
    private String roomId;
    private String title;
    private String sender;
    private Integer totalMemberCount;
    private Integer fee;
    private LocalDateTime closingTime;
}
