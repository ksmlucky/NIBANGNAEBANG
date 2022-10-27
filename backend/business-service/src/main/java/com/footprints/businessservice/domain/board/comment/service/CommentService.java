package com.footprints.businessservice.domain.board.comment.service;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.comment.dto.CommentReqDto;
import com.footprints.businessservice.domain.board.comment.dto.CommentResDto;

import java.util.List;

public interface CommentService {

    List<CommentResDto> getCommentList(ArticleDto id);

    void commentSave(String writer, Long id, CommentReqDto dto);


}
