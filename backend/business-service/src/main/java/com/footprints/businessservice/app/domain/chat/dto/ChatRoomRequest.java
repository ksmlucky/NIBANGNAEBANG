package com.footprints.businessservice.app.domain.chat.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ChatRoomRequest {
    private String title;
    private Integer totalUserCount;
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
//    private LocalDateTime closingTime;
    private Integer fee;
}
