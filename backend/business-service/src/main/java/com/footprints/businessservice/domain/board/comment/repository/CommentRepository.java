package com.footprints.businessservice.domain.board.comment.repository;

import com.footprints.businessservice.domain.board.comment.dto.CommentResDto;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
