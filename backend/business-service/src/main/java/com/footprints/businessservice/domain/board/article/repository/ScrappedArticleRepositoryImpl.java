package com.footprints.businessservice.domain.board.article.repository;

import com.footprints.businessservice.domain.board.article.entity.QScrappedArticle;
import com.footprints.businessservice.domain.board.article.entity.ScrappedArticle;
import com.footprints.businessservice.domain.board.article.repository.custom.ScrappedArticleRepositoryCustom;
import com.footprints.businessservice.domain.board.article.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ScrappedArticleRepositoryImpl extends QuerydslRepositorySupport implements ScrappedArticleRepositoryCustom {

    public ScrappedArticleRepositoryImpl() {
        super(ScrappedArticle.class);
    }


    @Override
    public Page<ScrappedArticle> getScrappedArticleList(Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(QScrappedArticle.scrappedArticle)
//                .where() // 현재 유저의 memberId와 scrappedArticle의 memberId가 같은 애들만
                ,
                countQuery -> countQuery
                        .selectFrom(QScrappedArticle.scrappedArticle)
        );
    }

}
