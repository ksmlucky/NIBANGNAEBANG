package com.footprints.businessservice.app.domain.board.article.repository.custom;

import com.footprints.businessservice.app.domain.board.article.entity.ScrappedArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScrappedArticleRepositoryCustom {
    Page<ScrappedArticle> getScrappedArticleList(Long memberId, String category, Pageable pageable);

    ScrappedArticle findByMemberIdAndArticleId(Long memberId, Long articleId);

    ScrappedArticle getScrappedArticle(Long articleId);
}
