package com.footprints.businessservice.domain.board.image.entity;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    private String fileName;

    private String originalName;

    private String url;

    private Long size;
}
