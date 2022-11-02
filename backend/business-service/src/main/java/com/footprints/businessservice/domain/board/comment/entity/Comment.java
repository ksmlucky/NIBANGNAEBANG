package com.footprints.businessservice.domain.board.comment.entity;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.reply.entity.Reply;
import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private String writer;
    private boolean isUpdated;
    private boolean isDeleted;

    @JoinColumn(name = "article_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @OneToMany(mappedBy = "comment")
    private Set<Reply> replies;

    public void updateContent(String content) {
        this.content = content;
    }

    public void changeIsUpdated() {
        this.isUpdated = true;
    }
    public void changeIsDeleted() {
        this.isDeleted = true;
    }
}
