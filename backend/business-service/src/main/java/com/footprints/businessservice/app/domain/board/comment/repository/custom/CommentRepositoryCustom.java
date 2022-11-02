package com.footprints.businessservice.app.domain.board.comment.repository.custom;

import com.footprints.businessservice.app.domain.board.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<Comment> getCommentList(Long articleId, Pageable pageable);

    Comment getComment(Long commentId);

}
