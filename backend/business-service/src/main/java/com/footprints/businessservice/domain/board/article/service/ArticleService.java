package com.footprints.businessservice.domain.board.article.service;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.dto.ArticleRequest;
import com.footprints.businessservice.domain.board.article.dto.SearchCondition;
import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArticleService {

    List<ArticleDto> getArticleList(SortCondition condition, Pageable pageable);

    void saveArticle(ArticleRequest request, List<MultipartFile> multipartFiles) throws IOException;

    ArticleDto getArticle(Long articleId);

    void likeArticle(String token, Long articleId);

    void unlikeArticle(String token, Long articleId);

    List<ArticleDto> searchArticle(SearchCondition condition, Pageable pageable);
}
