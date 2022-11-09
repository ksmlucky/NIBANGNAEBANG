package com.footprints.businessservice.app.domain.chat.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ChatRoomRequest {
    private String title;
    private Integer totalUserCount;
    private LocalDateTime closingTime;
    private Integer fee;
}
