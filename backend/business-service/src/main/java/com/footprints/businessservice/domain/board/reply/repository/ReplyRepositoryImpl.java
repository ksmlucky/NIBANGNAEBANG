package com.footprints.businessservice.domain.board.reply.repository;

import com.footprints.businessservice.domain.board.article.repository.support.QuerydslRepositorySupport;
import com.footprints.businessservice.domain.board.reply.entity.Reply;
import com.footprints.businessservice.domain.board.reply.repository.custom.ReplyRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.footprints.businessservice.domain.board.reply.entity.QReply.reply;

public class ReplyRepositoryImpl extends QuerydslRepositorySupport implements ReplyRepositoryCustom {

    public ReplyRepositoryImpl() {
        super(Reply.class);
    }

    @Override
    public Page<Reply> getReplyList(Long commentId, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(reply)
                        .where(reply.comment.id.eq(commentId)),
                countQuery -> countQuery
                        .selectFrom(reply)
                        .where(reply.comment.id.eq(commentId))
        );
    }
}
