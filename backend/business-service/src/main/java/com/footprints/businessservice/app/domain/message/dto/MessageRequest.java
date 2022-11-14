package com.footprints.businessservice.app.domain.message.dto;

import lombok.Data;

@Data
public class MessageRequest {
    private String receiveMember;
    private String title;
    private String content;
}
