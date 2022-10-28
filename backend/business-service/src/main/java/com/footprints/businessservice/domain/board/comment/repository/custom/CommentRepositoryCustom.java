package com.footprints.businessservice.domain.board.comment.repository.custom;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<Comment> getCommentList(Long articleId, Pageable pageable);
}
