package com.footprints.businessservice.domain.board.comment.dto;

import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.domain.board.reply.dto.ReplyDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class CommentDto {
    private Long id;
    private String content;
    private String writer;
    private Long articleId;
    private boolean isUpdated;
    private boolean isDeleted;
    private List<ReplyDto> replies;

    @QueryProjection
    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.writer = comment.getWriter();
        this.articleId = comment.getArticle().getId();
        this.isUpdated = comment.isUpdated();
        this.isDeleted = comment.isDeleted();
        this.replies = comment.getReplies().stream()
                .map(reply -> new ReplyDto(reply))
                .collect(Collectors.toList());
    }
}
