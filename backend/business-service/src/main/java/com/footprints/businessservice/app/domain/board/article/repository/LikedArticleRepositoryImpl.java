package com.footprints.businessservice.app.domain.board.article.repository;

import com.footprints.businessservice.app.domain.board.article.entity.LikedArticle;
import com.footprints.businessservice.app.domain.board.article.repository.custom.LikedArticleRepositoryCustom;
import com.footprints.businessservice.app.domain.board.article.repository.support.QuerydslRepositorySupport;

import static com.footprints.businessservice.app.domain.board.article.entity.QLikedArticle.likedArticle;

public class LikedArticleRepositoryImpl extends QuerydslRepositorySupport implements LikedArticleRepositoryCustom {
    public LikedArticleRepositoryImpl() {
        super(LikedArticle.class);
    }

    @Override
    public LikedArticle findByMemberIdAndArticleId(Long memberId, Long articleId) {
        return selectFrom(likedArticle)
                .where(
                        likedArticle.article.id.eq(articleId)
                                .and(likedArticle.memberId.eq(memberId))
                )
                .fetchOne();
    }

    @Override
    public LikedArticle findArticle(Long articleId) {
        return selectFrom(likedArticle)
                .where(likedArticle.article.id.eq(articleId))
                .fetchOne();
    }
}
