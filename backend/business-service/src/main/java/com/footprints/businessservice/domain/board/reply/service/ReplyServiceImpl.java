package com.footprints.businessservice.domain.board.reply.service;

import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.domain.board.comment.repository.CommentRepository;
import com.footprints.businessservice.domain.board.reply.dto.ReplyDto;
import com.footprints.businessservice.domain.board.reply.dto.ReplyRequest;
import com.footprints.businessservice.domain.board.reply.entity.Reply;
import com.footprints.businessservice.domain.board.reply.repository.ReplyRepository;
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
}
