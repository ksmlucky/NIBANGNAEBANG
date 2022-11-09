package com.footprints.businessservice.app.domain.board.comment.service;

import com.footprints.businessservice.app.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.app.domain.board.comment.dto.CommentRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentList(Long articleId, Pageable pageable);

    void saveComment(String memberId, CommentRequest request, Long articleId);

    void updateComment(String memberId, CommentRequest request, Long commentId);

    void deleteComment(String memberId, Long commentId);
}
