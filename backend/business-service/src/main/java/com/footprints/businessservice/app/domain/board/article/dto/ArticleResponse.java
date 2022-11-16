package com.footprints.businessservice.app.domain.board.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private List<ArticleDto> articles;

    private long count;
}
