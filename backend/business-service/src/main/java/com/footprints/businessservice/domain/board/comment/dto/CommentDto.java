package com.footprints.businessservice.domain.board.comment.dto;

import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;


@Data
public class CommentDto {
    private Long id;
    private String content;
    private String writer;
    private Long articleId;

    @QueryProjection
    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
        this.articleId = comment.getArticle().getId();
    }
}
