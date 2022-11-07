package com.footprints.businessservice.app.domain.board.comment.repository;

import com.footprints.businessservice.app.domain.board.article.repository.support.QuerydslRepositorySupport;
import com.footprints.businessservice.app.domain.board.comment.entity.Comment;
import com.footprints.businessservice.app.domain.board.comment.repository.custom.CommentRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {

    public CommentRepositoryImpl() {
        super(Comment.class);}

    @Override
    public Page<Comment> getCommentList(Long articleId, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(comment)
                        .where(comment.article.id.eq(articleId)),
                countQuery -> countQuery
                        .selectFrom(comment)
                        .where(comment.article.id.eq(articleId))
        );
    }

    @Override
    public Comment getComment(Long commentId) {
        return selectFrom(QComment.comment)
                .where(QComment.comment.id.eq(commentId))
                .fetchOne();
    }
}
