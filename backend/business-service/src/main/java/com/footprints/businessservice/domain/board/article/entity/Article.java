package com.footprints.businessservice.domain.board.article.entity;

import com.footprints.businessservice.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private Long memberId;

    @Column(length = 1500)
    private String content;

    private Integer hits;

    private Integer likes;

    private String category;
}
