package com.footprints.businessservice.app.domain.board.article.service;

import com.footprints.businessservice.app.domain.board.article.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    ArticleResponse getArticleList(SortCondition condition, Pageable pageable);

    void saveArticle(String memberId, CommonRequest request, List<MultipartFile> multipartFiles);

    ArticleDto getArticle(Long articleId);

    void likeArticle(String memberId, Long articleId);

    void unlikeArticle(String memberId, Long articleId);

    List<ArticleDto> searchArticle(SearchCondition condition, Pageable pageable);

    void scrapArticle(String memberId, Long articleId);

    void unscrapArticle(String memberId, Long articleId);

    List<ScrappedArticleDto> getScrappedArticleList(String memberId, String category, Pageable pageable);

    void updateArticle(String memberId, Long articleId, ArticleUpdateRequest articleUpdateRequest, List<MultipartFile> multipartFiles);

    void deleteArticle(String memberId, Long articleId);
}
