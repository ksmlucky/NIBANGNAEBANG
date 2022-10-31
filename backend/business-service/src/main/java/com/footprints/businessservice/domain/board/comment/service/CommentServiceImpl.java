package com.footprints.businessservice.domain.board.comment.service;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.repository.ArticleRepository;
import com.footprints.businessservice.domain.board.comment.dto.CommentRequest;
import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.domain.board.comment.repository.CommentRepository;
import com.footprints.businessservice.domain.board.util.TokenDecoder;
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
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    private final TokenDecoder tokenDecoder;

    @Override
    public List<CommentDto> getCommentList(Long articleId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<Comment> comments = commentRepository.getCommentList(articleId, pageRequest);

        List<CommentDto> result = comments.stream()
                .map(comment -> new CommentDto(comment))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void saveComment(CommentRequest request, Long articleId) {
        Article article = articleRepository.getArticle(articleId);
        Comment comment = Comment.builder()
                .content(request.getContent())
                .writer(request.getWriter())
                .article(article)
                .build();

        commentRepository.save(comment);
    }

//    @Override
//    @Transactional
//    public void update(CommentRequest request, Long commentId) {
//        Comment comment = Comment.builder()
//                .content(request.getContent())
//                .writer(request.getWriter())
//                .build();
//    }

    @Override
    @Transactional
    public void deleteComment(String token, Long commentId) {
        Long memberId = tokenDecoder.extractMember(token);

        Comment comment = commentRepository.getComment(commentId);
        commentRepository.delete(comment);
    }
}
