package com.footprints.businessservice.domain.board.reply.dto;

import com.footprints.businessservice.domain.board.reply.entity.Reply;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ReplyDto {
    private Long id;

    private String content;
    private String writer;
    private Long commentId;
    private boolean isUpdated;
    private boolean isDeleted;

    @QueryProjection
    public ReplyDto(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.writer = reply.getWriter();
        this.commentId = reply.getComment().getId();
        this.isUpdated = reply.isUpdated();
        this.isDeleted = reply.isDeleted();
    }
}
