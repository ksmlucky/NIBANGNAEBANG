package com.footprints.businessservice.app.domain.board.reply.service;

import com.footprints.businessservice.app.domain.board.reply.dto.ReplyRequest;
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
    public void saveReply(String memberId, ReplyRequest request, Long commentId) {
        Comment comment = commentRepository.getComment(commentId);

        // memberId로 해당 멤버의 닉네임을 찾아서 밑에서 build 해줌

        Reply reply = Reply.builder()
                .content(request.getContent())
//                .writer(request.getWriter())
                .comment(comment)
                .build();

        replyRepository.save(reply);
    }

    @Override
    @Transactional
    public void updateReply(String memberId, ReplyRequest request, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyException(ReplyExceptionType.NOT_FOUND_REPLY));

        // memberId로 해당 멤버의 닉네임을 찾아서 위의 reply.getWriter()와 비교 후 같으면 수정 가능

        reply.changeIsUpdated();
        reply.updateContent(request.getContent());
    }

    @Override
    @Transactional
    public void deleteReply(String memberId, Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyException(ReplyExceptionType.NOT_FOUND_REPLY));

        // memberId로 해당 멤버의 닉네임을 찾아서 위의 reply.getWriter()와 비교 후 같으면 수정 가능

        reply.changeIsDeleted();
    }
}
