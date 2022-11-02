package com.footprints.businessservice.domain.board.article.repository;

import com.footprints.businessservice.domain.board.article.entity.QScrappedArticle;
import com.footprints.businessservice.domain.board.article.entity.ScrappedArticle;
import com.footprints.businessservice.domain.board.article.repository.custom.ScrappedArticleRepositoryCustom;
import com.footprints.businessservice.domain.board.article.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.footprints.businessservice.domain.board.article.entity.QScrappedArticle.scrappedArticle;

public class ScrappedArticleRepositoryImpl extends QuerydslRepositorySupport implements ScrappedArticleRepositoryCustom {

    public ScrappedArticleRepositoryImpl() {
        super(ScrappedArticle.class);
    }


    @Override
    public Page<ScrappedArticle> getScrappedArticleList(Long memberId, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(scrappedArticle)
                        .where(scrappedArticle.memberId.eq(memberId)),
                countQuery -> countQuery
                        .selectFrom(scrappedArticle)
                        .where(scrappedArticle.memberId.eq(memberId))
        );
    }
}
