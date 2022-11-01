package com.footprints.businessservice.domain.board.article.dto;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.domain.board.transfer.dto.TransferDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ArticleDto {

    private Long id;

    private String title;

    private String writer;

    private String content;

    private Integer hits;

    private Integer likes;

    private String category;

    private List<CommentDto> comments;

    private LocalDateTime createdAt;

    private CategoryDto categoryDto;

    @QueryProjection
    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.hits = article.getHits();
        this.likes = article.getLikes();
        this.category = article.getCategory();
        this.comments = article.getComments().stream()
                .map(comment -> new CommentDto(comment))
                .collect(Collectors.toList());
        this.createdAt = article.getCreatedAt();
    }

    public ArticleDto(Article article, List<CommentDto> comments) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.hits = article.getHits();
        this.likes = article.getLikes();
        this.category = article.getCategory();
        this.comments = comments;
        this.createdAt = article.getCreatedAt();
    }

    public ArticleDto(Article article, List<CommentDto> comments, CategoryDto categoryDto) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.hits = article.getHits();
        this.likes = article.getLikes();
        this.category = article.getCategory();
        this.comments = comments;
        this.createdAt = article.getCreatedAt();

        if (article.getCategory().equals("transfer")) {
            this.categoryDto = new CategoryDto(categoryDto.getTransferDto());
        } else if (article.getCategory().equals("room")) {
            this.categoryDto = new CategoryDto(categoryDto.getRoomDto());
        }
    }
}
