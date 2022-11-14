package com.footprints.businessservice.app.domain.board.reply.service;

import com.footprints.businessservice.app.domain.board.comment.exception.CommentException;
import com.footprints.businessservice.app.domain.board.comment.exception.CommentExceptionType;
import com.footprints.businessservice.app.domain.board.reply.dto.ReplyRequest;
import com.footprints.businessservice.app.domain.board.reply.exception.ReplyException;
import com.footprints.businessservice.app.domain.board.reply.exception.ReplyExceptionType;
import com.footprints.businessservice.app.domain.board.reply.repository.ReplyRepository;
import com.footprints.businessservice.app.domain.board.comment.entity.Comment;
import com.footprints.businessservice.app.domain.board.comment.repository.CommentRepository;
import com.footprints.businessservice.app.domain.board.reply.dto.ReplyDto;
import com.footprints.businessservice.app.domain.board.reply.entity.Reply;
import com.footprints.businessservice.app.domain.member.MemberServiceClient;
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
    private final MemberServiceClient memberServiceClient;


    @Override
    public List<ReplyDto> getReplyList(Long commentId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Reply> replies = replyRepository.getReplyList(commentId, pageRequest);

        List<ReplyDto> result = replies.stream()
                .map(ReplyDto::new)
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void saveReply(String memberId, ReplyRequest request, Long commentId) {
        Comment comment = commentRepository.getComment(commentId);

        // memberId로 해당 멤버의 닉네임을 찾아서 밑에서 build 해줌
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        Reply reply = Reply.builder()
                .content(request.getContent())
                .writer(nickname)
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
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        if (reply.getWriter().equals(nickname)) {
            reply.updateContent(request.getContent());
            reply.changeIsUpdated();
        } else {
            throw new ReplyException(ReplyExceptionType.NOT_MATCH_NICKNAME);
        }
    }

    @Override
    @Transactional
    public void deleteReply(String memberId, Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyException(ReplyExceptionType.NOT_FOUND_REPLY));

        // memberId로 해당 멤버의 닉네임을 찾아서 위의 reply.getWriter()와 비교 후 같으면 수정 가능
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        if (reply.getWriter().equals(nickname)) {
            reply.changeIsDeleted();
        } else {
            throw new ReplyException(ReplyExceptionType.NOT_MATCH_NICKNAME);
        }
    }
}
