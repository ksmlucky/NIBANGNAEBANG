package com.footprints.businessservice.app.domain.board.article.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticleUpdateRequest {

    private String title;

    private String content;

    private List<Long> images;
}
