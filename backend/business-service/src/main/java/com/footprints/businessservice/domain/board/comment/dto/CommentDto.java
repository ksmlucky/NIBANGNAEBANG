package com.footprints.businessservice.domain.board.comment.dto;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String writer;
    private Article articleId;

    @QueryProjection
    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreateAt();
        this.updatedAt = comment.getUpdatedAt();
        this.writer = comment.getWriter();
        this.articleId = comment.getArticleId();
    }
}
