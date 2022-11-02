package com.footprints.businessservice.app.domain.board.article.dto;

import com.footprints.businessservice.app.domain.board.article.entity.ScrappedArticle;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrappedArticleDto {
    private Long id;

    private Long articleId;

    @QueryProjection
    public ScrappedArticleDto(ScrappedArticle scrappedArticle) {
        this.id = scrappedArticle.getId();
        this.articleId = scrappedArticle.getArticle().getId();
    }
}
