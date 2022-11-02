package com.footprints.businessservice.domain.board.article.entity;

import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;

    private String writer;

    @Column(length = 1500)
    private String content;

    private Integer hits;

    private Integer likes;

    private String category;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

    public void updateLikes(Integer count) {
        this.likes += count;
    }

    public void updateHits() {
        this.hits++;
    }
}
