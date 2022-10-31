package com.footprints.businessservice.domain.board.comment.repository;

import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.domain.board.comment.repository.custom.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

}
