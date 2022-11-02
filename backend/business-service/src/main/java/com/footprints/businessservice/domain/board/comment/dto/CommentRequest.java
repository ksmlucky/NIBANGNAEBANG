package com.footprints.businessservice.domain.board.comment.dto;
import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private String writer;
}
