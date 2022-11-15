package com.footprints.businessservice.app.domain.board.article.repository;

import com.footprints.businessservice.app.domain.board.article.dto.SearchCondition;
import com.footprints.businessservice.app.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.article.repository.custom.ArticleRepositoryCustom;
import com.footprints.businessservice.app.domain.board.article.repository.support.QuerydslRepositorySupport;
import com.footprints.businessservice.app.domain.board.comment.entity.Comment;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.footprints.businessservice.app.domain.board.article.entity.QArticle.article;
import static com.footprints.businessservice.app.domain.board.comment.entity.QComment.comment;
import static com.footprints.businessservice.app.domain.board.image.entity.QImage.image;
import static com.footprints.businessservice.app.domain.board.reply.entity.QReply.reply;
import static com.footprints.businessservice.app.domain.board.transfer.entity.QTransfer.transfer;

public class ArticleRepositoryImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public Page<Article> getArticleList(SortCondition condition, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(article)
                        .leftJoin(article.transfer, transfer)
                        .fetchJoin()
                        .leftJoin(article.images, image)
                        .where(
                                categoryEq(condition.getCategory()),
                                addressEq(condition.getAddress())
                        ),
                countQuery -> countQuery
                        .selectFrom(article)
                        .leftJoin(article.transfer, transfer)
                        .fetchJoin()
                        .leftJoin(article.images, image)
                        .where(
                                categoryEq(condition.getCategory()),
                                addressEq(condition.getAddress())
                        )
        );
    }

    private BooleanExpression addressEq(String address) {
        return StringUtils.hasText(address) ? transfer.address.contains(address) : null;
    }

    @Override
    public Article getArticle(Long articleId) {
        return selectFrom(article)
                .leftJoin(article.images, image)
                .fetchJoin()
                .where(articleEq(articleId))
                .fetchOne();
    }

    @Override
    public Article getArticleWithNicknameAndArticleId(String nickname, Long articleId) {
        return selectFrom(article)
                .leftJoin(article.images, image)
                .fetchJoin()
                .where(nicknameAndArticleEq(nickname, articleId))
                .fetchOne();
    }


    @Override
    public Article getArticleWithNickname(String nickname, Long articleId) {
        return selectFrom(article)
                .where(nicknameAndArticleEq(nickname, articleId))
                .fetchOne();
    }

    @Override
    public List<Comment> getCommentList(Long articleId) {
        return selectFrom(comment)
                .distinct()
                .leftJoin(comment.article, article)
                .fetchJoin()
                .leftJoin(comment.replies, reply)
                .fetchJoin()
                .where(articleEq(articleId))
                .fetch();
    }

    private static BooleanExpression articleEq(Long articleId) {
        return article.id.eq(articleId);
    }

    @Override
    public Page<Article> searchArticle(SearchCondition condition, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(article)
                        .innerJoin(article.transfer, transfer)
                        .fetchJoin()
                        .where(
                                titleContains(condition.getTitle()),
                                writerContains(condition.getWriter()),
                                contentContains(condition.getContent())
                        )
                        .orderBy(article.createdAt.desc()),
                countQuery -> countQuery
                        .selectFrom(article)
                        .innerJoin(article.transfer, transfer)
                        .fetchJoin()
                        .where(
                                titleContains(condition.getTitle()),
                                writerContains(condition.getWriter()),
                                contentContains(condition.getContent())
                        )
                        .orderBy(article.createdAt.desc())
        );
    }

    private BooleanExpression categoryEq(String category) {
        return StringUtils.hasText(category) ? article.category.eq(category) : null;
    }

    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? article.title.contains(title) : null;
    }

    private BooleanExpression writerContains(String writer) {
        return StringUtils.hasText(writer) ? article.writer.contains(writer) : null;
    }

    private BooleanExpression contentContains(String content) {
        return StringUtils.hasText(content) ? article.content.contains(content) : null;
    }

    private BooleanExpression nicknameAndArticleEq(String nickname, Long articleId) {
        return article.writer.eq(nickname).and(articleEq(articleId));
    }

}
