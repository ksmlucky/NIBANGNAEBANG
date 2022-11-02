package com.footprints.businessservice.domain.board.article.service;

import com.footprints.businessservice.domain.board.article.dto.*;
import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> getArticleList(SortCondition condition, Pageable pageable);

    void saveArticle(String memberId, CommonRequest request);

    ArticleDto getArticle(Long articleId);

    void likeArticle(String memberId, Long articleId);

    void unlikeArticle(String memberId, Long articleId);

    List<ArticleDto> searchArticle(SearchCondition condition, Pageable pageable);

    void scrapArticle(Long articleId);

    List<ScrappedArticleDto> getScrappedArticleList(Pageable pageable);
}
