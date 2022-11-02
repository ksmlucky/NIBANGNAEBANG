package com.footprints.businessservice.app.domain.board.reply.repository.custom;

import com.footprints.businessservice.app.domain.board.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyRepositoryCustom {
    Page<Reply> getReplyList(Long commentId, Pageable pageable);

    Reply getReply(Long replyId);
}
