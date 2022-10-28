package com.footprints.businessservice.domain.board.comment.repository;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.domain.board.comment.repository.custom.CommentRepositoryCustom;
import com.footprints.businessservice.domain.board.comment.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import static com.footprints.businessservice.domain.board.article.entity.QArticle.article;
import static com.footprints.businessservice.domain.board.comment.entity.QComment.comment;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {

    public CommentRepositoryImpl() {
        super(Comment.class);}

    @Override
    public Page<Comment> getCommentList(Article articleId, Pageable pageable) {
//        return applyPagination(pageable, contentQuery -> contentQuery
//                        .selectFrom(comment)
//                        .where(),
//                countQuery -> countQuery
//                        .selectFrom(comment)
//                        .where(categoryEq(condition.getCategory()))
//                        .orderBy(sort(pageable))
//        );
        return null;
    }
    private BooleanExpression articlIdEq(String category) {
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
