package com.footprints.businessservice.domain.board.article.service;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getArticleList(SortCondition condition, Pageable pageable);
}
