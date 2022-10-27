package com.footprints.businessservice.domain.board.comment.service;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.repository.ArticleRepository;
import com.footprints.businessservice.domain.board.comment.dto.CommentReqDto;
import com.footprints.businessservice.domain.board.comment.dto.CommentResDto;
import com.footprints.businessservice.domain.board.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final ArticleRepository articleRepository;

    @Override
    public List<CommentResDto> getCommentList(ArticleDto id) {

//        return commentRepository.findAll();
        return null;
    }

    @Override
    public void commentSave(String writer, Long id, CommentReqDto dto) {
//        Article article = articleRepository.
    }
}
