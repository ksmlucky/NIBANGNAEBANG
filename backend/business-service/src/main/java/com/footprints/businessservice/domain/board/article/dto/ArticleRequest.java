package com.footprints.businessservice.domain.board.article.dto;

import lombok.Data;

@Data
public class ArticleRequest {

    private String title;

    private String writer;

    private String content;

    private String category;

}
