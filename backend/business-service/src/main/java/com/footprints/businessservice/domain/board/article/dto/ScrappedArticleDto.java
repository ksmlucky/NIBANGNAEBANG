package com.footprints.businessservice.domain.board.article.dto;

import com.footprints.businessservice.domain.board.article.entity.ScrappedArticle;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrappedArticleDto {
    private Long id;

//    private Long memberId;

    private Long articleid;

    @QueryProjection
    public ScrappedArticleDto(ScrappedArticle scrappedArticle) {
        this.id = scrappedArticle.getId();
        this.articleid = scrappedArticle.getArticle().getId();
    }
}
