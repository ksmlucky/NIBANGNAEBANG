package com.footprints.businessservice.app.domain.board.article.entity;

import com.footprints.businessservice.app.domain.board.comment.entity.Comment;
import com.footprints.businessservice.app.domain.board.image.entity.Image;
import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<ScrappedArticle> scrappedArticles;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Image> images;

    public void updateLikes(Integer count) {
        this.likes += count;
    }

    public void updateHits() {
        this.hits++;
    }
}
