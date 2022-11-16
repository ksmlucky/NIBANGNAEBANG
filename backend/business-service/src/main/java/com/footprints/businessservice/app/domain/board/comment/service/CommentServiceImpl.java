package com.footprints.businessservice.app.domain.board.comment.service;

import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.article.exception.ArticleException;
import com.footprints.businessservice.app.domain.board.article.exception.ArticleExceptionType;
import com.footprints.businessservice.app.domain.board.article.repository.ArticleRepository;
import com.footprints.businessservice.app.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.app.domain.board.comment.exception.CommentException;
import com.footprints.businessservice.app.domain.board.comment.exception.CommentExceptionType;
import com.footprints.businessservice.app.domain.board.comment.dto.CommentRequest;
import com.footprints.businessservice.app.domain.board.comment.entity.Comment;
import com.footprints.businessservice.app.domain.board.comment.repository.CommentRepository;
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
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    private final MemberServiceClient memberServiceClient;

    @Override
    public List<CommentDto> getCommentList(Long articleId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Comment> comments = commentRepository.getCommentList(articleId, pageRequest);

        List<CommentDto> result = comments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void saveComment(String memberId, CommentRequest request, Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleExceptionType.NOT_FOUND_ARTICLE));

        // memberId로 해당 멤버의 닉네임을 찾아서 밑에서 build 해줌
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        Comment comment = Comment.builder()
                .content(request.getContent())
                .writer(nickname)
                .article(article)
                .build();

        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(String memberId, CommentRequest request, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(CommentExceptionType.NOT_FOUND_COMMENT));

        // memberId로 해당 멤버의 닉네임을 찾아서 위의 comment.getWriter()와 비교 후 같으면 수정 가능
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        if (comment.getWriter().equals(nickname)) {
            comment.updateContent(request.getContent());
            comment.changeIsUpdated();
        } else {
            throw new CommentException(CommentExceptionType.NOT_MATCH_NICKNAME);
        }
    }


    @Override
    @Transactional
    public void deleteComment(String memberId, Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(CommentExceptionType.NOT_FOUND_COMMENT));

        // memberId로 해당 멤버의 닉네임을 찾아서 위의 comment.getWriter()와 비교 후 같으면 삭제 가능
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        if (comment.getWriter().equals(nickname)) {
            comment.changeIsDeleted();
        } else {
            throw new CommentException(CommentExceptionType.NOT_MATCH_NICKNAME);
        }
    }
}
