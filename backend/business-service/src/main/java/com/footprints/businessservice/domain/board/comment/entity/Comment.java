package com.footprints.businessservice.domain.board.comment.entity;

import com.footprints.businessservice.domain.board.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @JoinColumn(name = "board_id")
    @ManyToOne
    private Article boardId;

    private String writer;

    private String createAt;

    private String updatedAt;
}
