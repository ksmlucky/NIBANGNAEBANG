package com.footprints.businessservice.domain.board.article.repository.custom;

import com.footprints.businessservice.domain.board.article.entity.LikedArticle;


public interface LikedArticleRepositoryCustom {
    LikedArticle findByMemberIdAndArticleId(Long memberId, Long articleId);
    LikedArticle findArticle(Long articleId);
}
