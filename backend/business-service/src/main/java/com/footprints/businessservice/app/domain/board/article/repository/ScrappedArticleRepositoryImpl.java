package com.footprints.businessservice.app.domain.board.article.repository;

import com.footprints.businessservice.app.domain.board.article.entity.ScrappedArticle;
import com.footprints.businessservice.app.domain.board.article.repository.custom.ScrappedArticleRepositoryCustom;
import com.footprints.businessservice.app.domain.board.article.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.footprints.businessservice.app.domain.board.article.entity.QScrappedArticle.scrappedArticle;

public class ScrappedArticleRepositoryImpl extends QuerydslRepositorySupport implements ScrappedArticleRepositoryCustom {

    public ScrappedArticleRepositoryImpl() {
        super(ScrappedArticle.class);
    }


    @Override
    public Page<ScrappedArticle> getScrappedArticleList(Long memberId, String category, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(scrappedArticle)
                        .where(scrappedArticle.memberId.eq(memberId)
                                .and(scrappedArticle.article.category.eq(category))
                        ),
                countQuery -> countQuery
                        .selectFrom(scrappedArticle)
                        .where(scrappedArticle.memberId.eq(memberId)
                                .and(scrappedArticle.article.category.eq(category))
                        )
        );
    }

    @Override
    public ScrappedArticle findByMemberIdAndArticleId(Long memberId, Long articleId) {
        return selectFrom(scrappedArticle)
                .where(
                        scrappedArticle.article.id.eq(articleId)
                                .and(scrappedArticle.memberId.eq(memberId))
                )
                .fetchOne();
    }

    @Override
    public ScrappedArticle getScrappedArticle(Long articleId) {
        return selectFrom(scrappedArticle)
                .where(scrappedArticle.article.id.eq(articleId))
                .fetchOne();
    }
}
