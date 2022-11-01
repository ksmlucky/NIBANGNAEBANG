package com.footprints.businessservice.domain.board.article.dto;

import lombok.Data;

@Data
public class ArticleRequest {

    private String title;

    private String writer;

    private String content;

    private String category;

    public ArticleRequest(ArticleRequest articleRequest) {
        this.title = articleRequest.getTitle();
        this.writer = articleRequest.getWriter();
        this.content = articleRequest.getContent();
        this.category = articleRequest.getCategory();
    }
}
