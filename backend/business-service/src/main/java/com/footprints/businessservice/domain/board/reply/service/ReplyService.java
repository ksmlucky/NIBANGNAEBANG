package com.footprints.businessservice.domain.board.reply.service;

import com.footprints.businessservice.domain.board.reply.dto.ReplyDto;
import com.footprints.businessservice.domain.board.reply.dto.ReplyRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReplyService {
    List<ReplyDto> getReplyList(Long commentId, Pageable pageable);

    void saveReply(ReplyRequest request, Long CommentId);

    void deleteReply(String token, Long replyId);
}
