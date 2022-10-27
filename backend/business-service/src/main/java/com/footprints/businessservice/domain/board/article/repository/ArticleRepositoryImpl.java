package com.footprints.businessservice.domain.board.article.repository;

import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.repository.custom.ArticleRepositoryCustom;
import com.footprints.businessservice.domain.board.article.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import static com.footprints.businessservice.domain.board.article.entity.QArticle.article;

public class ArticleRepositoryImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public Page<Article> getArticleList(SortCondition condition, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(article)
                        .where(categoryEq(condition.getCategory()))
                        .orderBy(sort(pageable)),
                countQuery -> countQuery
                        .selectFrom(article)
                        .where(categoryEq(condition.getCategory()))
                        .orderBy(sort(pageable))
        );
    }

    private BooleanExpression categoryEq(String category) {
        return StringUtils.hasText(category) ? article.category.eq(category) : null;
    }

    private OrderSpecifier<?> sort(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "hits":
                        return new OrderSpecifier(direction, article.hits);
                    case "likes":
                        return new OrderSpecifier(direction, article.likes);
                }
            }
        }

        return null;
    }
}
