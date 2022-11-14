package com.footprints.businessservice.app.domain.board.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    private String title;

    private String content;

    private String category;

    public ArticleRequest(ArticleRequest articleRequest) {
        this.title = articleRequest.getTitle();
        this.content = articleRequest.getContent();
        this.category = articleRequest.getCategory();
    }
}
