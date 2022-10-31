package com.footprints.businessservice.domain.board.reply.dto;

import lombok.Data;

@Data
public class ReplyRequest {
    private String content;
    private String writer;
}
