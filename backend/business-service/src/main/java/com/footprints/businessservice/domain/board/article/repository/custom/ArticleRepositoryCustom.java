package com.footprints.businessservice.domain.board.article.repository.custom;

import com.footprints.businessservice.domain.board.article.dto.SearchCondition;
import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.domain.board.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
    Page<Article> getArticleList(SortCondition condition, Pageable pageable);
    Article getArticle(Long articleId);
    Page<Article> searchArticle(SearchCondition condition, Pageable pageable);
}
