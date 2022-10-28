package com.footprints.businessservice.domain.board.comment.service;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.repository.ArticleRepository;
import com.footprints.businessservice.domain.board.comment.dto.CommentRequest;
import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.domain.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

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
        articleRepository
        Comment comment = Comment.builder()
                .content(request.getContent())
                .writer(request.getWriter())
                .createAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                .articleId()
                .build();

        commentRepository.save(comment);
    }
}
