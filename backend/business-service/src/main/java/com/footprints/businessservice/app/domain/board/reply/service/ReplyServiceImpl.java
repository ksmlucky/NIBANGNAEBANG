package com.footprints.businessservice.app.domain.board.reply.service;

import com.footprints.businessservice.app.domain.board.reply.dto.ReplyRequest;
import com.footprints.businessservice.app.domain.board.reply.dto.ReplyUpdateRequest;
import com.footprints.businessservice.app.domain.board.reply.exception.ReplyException;
import com.footprints.businessservice.app.domain.board.reply.exception.ReplyExceptionType;
import com.footprints.businessservice.app.domain.board.reply.repository.ReplyRepository;
import com.footprints.businessservice.app.domain.board.comment.entity.Comment;
import com.footprints.businessservice.app.domain.board.comment.repository.CommentRepository;
import com.footprints.businessservice.app.domain.board.reply.dto.ReplyDto;
import com.footprints.businessservice.app.domain.board.reply.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;


    @Override
    public List<ReplyDto> getReplyList(Long commentId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Reply> replies = replyRepository.getReplyList(commentId, pageRequest);

        List<ReplyDto> result = replies.stream()
                .map(reply -> new ReplyDto(reply))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void saveReply(ReplyRequest request, Long commentId) {
        Comment comment = commentRepository.getComment(commentId);
        Reply reply = Reply.builder()
                .content(request.getContent())
                .writer(request.getWriter())
                .comment(comment)
                .build();

        replyRepository.save(reply);
    }

    @Override
    @Transactional
    public void updateReply(ReplyUpdateRequest request, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyException(ReplyExceptionType.NOT_FOUND_REPLY));

        reply.changeIsUpdated();
        reply.updateContent(request.getContent());
    }

    @Override
    @Transactional
    public void deleteReply(String token, Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyException(ReplyExceptionType.NOT_FOUND_REPLY));

        reply.changeIsDeleted();
    }
}
