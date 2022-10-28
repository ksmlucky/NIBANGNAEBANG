package com.footprints.businessservice.domain.board.article.service;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.dto.ArticleRequest;
import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.entity.LikedArticle;
import com.footprints.businessservice.domain.board.article.repository.ArticleRepository;
import com.footprints.businessservice.domain.board.article.repository.LikedArticleRepository;
import com.footprints.businessservice.domain.board.util.TokenDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final LikedArticleRepository likedArticleRepository;
    private final TokenDecoder tokenDecoder;

    @Override
    public List<ArticleDto> getArticleList(SortCondition condition, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, condition.getSort()));

        Page<Article> articles = articleRepository.getArticleList(condition, pageRequest);

        List<ArticleDto> result = articles.stream()
                .map(article -> new ArticleDto(article))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void saveArticle(ArticleRequest request) {
        Article article = Article.builder()
                .title(request.getTitle())
                .writer(request.getWriter())
                .content(request.getContent())
                .hits(0)
                .likes(0)
                .category(request.getCategory())
                .build();

        articleRepository.save(article);
    }

    @Override
    public ArticleDto getArticle(Long articleId) {
        Article article = articleRepository.getArticle(articleId);
        return new ArticleDto(article);
    }

    @Override
    public void likeArticle(String token, Long articleId) {
        Long memberId = tokenDecoder.extractMember(token);
        if (findLikedArticleWithMemberIdAndArticleId(memberId, articleId) != null) {
            throw new RuntimeException("예외 처리 만들기 !!");
        }

        Article article = articleRepository.getArticle(articleId);
        if (article == null) {
            throw new RuntimeException("예외 처리 만들기 !!");
        }

        LikedArticle likedArticle = LikedArticle.builder()
                .article(article)
                .memberId(memberId)
                .build();

        likedArticleRepository.save(likedArticle);
    }

    @Override
    public void unlikeArticle(String token, Long articleId) {
        Long memberId = tokenDecoder.extractMember(token);
        if (findLikedArticleWithMemberIdAndArticleId(memberId, articleId) == null) {
            throw new RuntimeException("예외 처리 만들기 !!");
        }

        LikedArticle likedArticle = likedArticleRepository.findArticle(articleId);
        likedArticleRepository.delete(likedArticle);
    }

    private LikedArticle findLikedArticleWithMemberIdAndArticleId(Long memberId, Long articleId) {
        return likedArticleRepository.findByMemberIdAndArticleId(memberId, articleId);
    }
}
