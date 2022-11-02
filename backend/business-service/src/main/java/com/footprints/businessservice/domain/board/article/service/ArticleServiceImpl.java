package com.footprints.businessservice.domain.board.article.service;

import com.footprints.businessservice.domain.board.article.dto.*;
import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.entity.LikedArticle;
import com.footprints.businessservice.domain.board.article.entity.ScrappedArticle;
import com.footprints.businessservice.domain.board.article.exception.ArticleException;
import com.footprints.businessservice.domain.board.article.exception.ArticleExceptionType;
import com.footprints.businessservice.domain.board.article.repository.ArticleRepository;
import com.footprints.businessservice.domain.board.article.repository.LikedArticleRepository;
import com.footprints.businessservice.domain.board.article.repository.ScrappedArticleRepository;
import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.domain.board.transfer.dto.TransferDto;
import com.footprints.businessservice.domain.board.transfer.entity.Transfer;
import com.footprints.businessservice.domain.board.transfer.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final LikedArticleRepository likedArticleRepository;
    private final ScrappedArticleRepository scrappedArticleRepository;
    private final TransferRepository transferRepository;

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
    public void saveArticle(String memberId, CommonRequest request) {

        // Auth-Service 에 memberId로 로그인한 사용자 이름 조회 후 writer 필드에 저장

        Article article = Article.builder()
                .title(request.getArticleRequest().getTitle())
                .writer(request.getArticleRequest().getWriter())
                .content(request.getArticleRequest().getContent())
                .hits(0)
                .likes(0)
                .category(request.getArticleRequest().getCategory())
                .build();

        if (request.getArticleRequest().getCategory().equals("transfer")) {
            Transfer transfer = Transfer.builder()
                    .roomType(request.getTransferRequest().getRoomType())
                    .buildingType(request.getTransferRequest().getBuildingType())
                    .article(article)
                    .build();

            transferRepository.save(transfer);
        }

        articleRepository.save(article);
    }

    @Override
    public ArticleDto getArticle(Long articleId) {
        Article article = articleRepository.getArticle(articleId);

        if (article == null) {
            throw new ArticleException(ArticleExceptionType.NOT_FOUND_ARTICLE);
        }

        article.updateHits();

        List<CommentDto> comments = article.getComments().stream()
                .map(comment -> new CommentDto(comment))
                .collect(Collectors.toList());

        if (article.getCategory().equals("transfer")) {
            Transfer transfer = transferRepository.getTransferByArticleId(articleId);
            TransferDto transferDto = transfer.toDto(transfer);

            return new ArticleDto(article, comments, new CategoryDto(transferDto));
        }

        return new ArticleDto(article, comments);
    }

    @Override
    @Transactional
    public void likeArticle(String memberId, Long articleId) {
        Long findMemberId = Long.parseLong(memberId);

        if (findLikedArticleWithMemberIdAndArticleId(findMemberId, articleId) != null) {
            throw new ArticleException(ArticleExceptionType.ALREADY_LIKED_ARTICLE);
        }

        Article article = updateLikeCount(articleId, 1);

        LikedArticle likedArticle = LikedArticle.builder()
                .article(article)
                .memberId(findMemberId)
                .build();

        likedArticleRepository.save(likedArticle);
    }

    @Override
    @Transactional
    public void unlikeArticle(String memberId, Long articleId) {
        Long findMemberId = Long.parseLong(memberId);

        if (findLikedArticleWithMemberIdAndArticleId(findMemberId, articleId) == null) {
            throw new ArticleException(ArticleExceptionType.NOT_LIKED_ARTICLE);
        }

        updateLikeCount(articleId, -1);

        LikedArticle likedArticle = likedArticleRepository.findArticle(articleId);
        likedArticleRepository.delete(likedArticle);
    }

    @Override
    public List<ArticleDto> searchArticle(SearchCondition condition, Pageable pageable) {
        Page<Article> articles = articleRepository.searchArticle(condition, pageable);

        List<ArticleDto> result = articles.stream()
                .map(article -> new ArticleDto(article))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void scrapArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleExceptionType.NOT_FOUND_ARTICLE));

        ScrappedArticle scrappedArticle = ScrappedArticle.builder()
                .article(article)
                .build();

        scrappedArticleRepository.save(scrappedArticle);
    }

    @Override
    public List<ScrappedArticleDto> getScrappedArticleList(String memberId, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        Page<ScrappedArticle> scrappedArticles = scrappedArticleRepository.getScrappedArticleList(Long.parseLong(memberId), pageRequest);

        List<ScrappedArticleDto> result = scrappedArticles.stream()
                .map(scrappedArticle -> new ScrappedArticleDto(scrappedArticle))
                .collect(Collectors.toList());

        return result;
    }

    private Article updateLikeCount(Long articleId, int count) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleExceptionType.NOT_FOUND_ARTICLE));

        if (article == null) {
            throw new ArticleException(ArticleExceptionType.NOT_FOUND_ARTICLE);
        }

        article.updateLikes(count);
        return article;
    }

    private LikedArticle findLikedArticleWithMemberIdAndArticleId(Long memberId, Long articleId) {
        return likedArticleRepository.findByMemberIdAndArticleId(memberId, articleId);
    }
}
