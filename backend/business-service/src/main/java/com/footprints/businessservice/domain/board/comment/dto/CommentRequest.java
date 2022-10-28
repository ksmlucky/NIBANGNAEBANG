package com.footprints.businessservice.domain.board.comment.dto;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CommentRequest {
    private Long id;
    private String content;
    private String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private Article articleId;

//    public Comment toEntity() {
//        Comment comment = Comment.builder()
//                .id(id)
//                .content(content)
//                .createAt(createdAt)
//                .updatedAt(updatedAt)
//                .articleId(articleId)
//                .build();
//
//        return comment;
//    }
}
