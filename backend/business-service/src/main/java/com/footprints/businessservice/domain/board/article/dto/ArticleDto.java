package com.footprints.businessservice.domain.board.article.dto;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDto {

    private Long id;

    private String title;

    private String writer;

    private String content;

    private Integer hits;

    private Integer likes;

    private String category;

    private LocalDateTime createdAt;

    @QueryProjection
    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.hits = article.getHits();
        this.likes = article.getLikes();
        this.category = article.getCategory();
        this.createdAt = article.getCreatedAt();
    }
}
