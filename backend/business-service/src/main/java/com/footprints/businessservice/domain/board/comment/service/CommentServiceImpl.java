package com.footprints.businessservice.domain.board.comment.service;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.repository.ArticleRepository;
import com.footprints.businessservice.domain.board.comment.dto.CommentRequest;
import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.domain.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> getCommentList(Article articleId, Pageable pageable) {
        return null;
    }

    @Override
    public void saveComment(CommentRequest request) {

    }
}
