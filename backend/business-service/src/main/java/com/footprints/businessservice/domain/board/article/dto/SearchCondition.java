package com.footprints.businessservice.domain.board.article.dto;

import lombok.Data;

@Data
public class SearchCondition {

    private String category;

    private String title;

    private String writer;

    private String content;
}
