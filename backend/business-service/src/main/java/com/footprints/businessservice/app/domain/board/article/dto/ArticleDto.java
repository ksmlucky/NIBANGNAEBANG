package com.footprints.businessservice.app.domain.board.article.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.app.domain.board.image.dto.ImageDto;
import com.footprints.businessservice.app.domain.board.image.entity.Image;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDto {

    private Long id;

    private String title;

    private String writer;

    private String content;

    private Integer hits;

    private Integer likes;

    private String category;

    private ImageDto image;

    private List<CommentDto> comments;

    private List<ImageDto> images;

    private LocalDateTime createdAt;

    private CategoryDto categoryDetail;

    @QueryProjection
    public ArticleDto(Article article) {
        ImageDto image = null;
        if (!article.getImages().isEmpty()) {
            image = imageToImageDto(article);
        }

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
        this.image = image != null ? image : null;
        this.createdAt = article.getCreatedAt();
    }

    private ImageDto imageToImageDto(Article article) {
        Image findImage = article.getImages().get(0);
        ImageDto image = ImageDto.builder()
                .id(findImage.getId())
                .imageName(findImage.getOriginalFileName())
                .url(findImage.getUrl())
                .size(findImage.getSize())
                .build();

        return image;
    }

    public ArticleDto(Article article, List<CommentDto> comments, List<ImageDto> images) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.hits = article.getHits();
        this.likes = article.getLikes();
        this.category = article.getCategory();
        this.comments = comments;
        this.createdAt = article.getCreatedAt();
        this.images = images;
    }

    public ArticleDto(Article article, List<CommentDto> comments, CategoryDto categoryDto, List<ImageDto> images) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.hits = article.getHits();
        this.likes = article.getLikes();
        this.category = article.getCategory();
        this.comments = comments;
        this.images = images;
        this.createdAt = article.getCreatedAt();

        if (article.getCategory().equals("transfer")) {
            this.categoryDetail = new CategoryDto(categoryDto.getTransfer());
        }
    }
}
