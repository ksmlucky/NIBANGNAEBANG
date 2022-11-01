package com.footprints.businessservice.domain.board.reply.entity;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private String content;

    private String writer;

    private boolean isDeleted;

    private boolean isUpdated;

    // 댓글 식별키
    @JoinColumn(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    public void updateContent(String content) {
        this.content = content;
    }
}
