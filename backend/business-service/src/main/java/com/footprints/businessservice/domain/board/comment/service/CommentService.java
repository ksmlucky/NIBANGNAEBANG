package com.footprints.businessservice.domain.board.comment.service;

import com.footprints.businessservice.domain.board.comment.dto.CommentRequest;
import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.domain.board.comment.dto.CommentUpdateRequest;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentList(Long articleId, Pageable pageable);

    void saveComment(CommentRequest request, Long articleId);

    void updateComment(CommentUpdateRequest request, Long commentId);

    void deleteComment(String memberId, Long commentId);
}
