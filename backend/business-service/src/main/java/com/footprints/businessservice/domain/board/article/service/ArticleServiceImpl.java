package com.footprints.businessservice.domain.board.article.service;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.dto.ArticleRequest;
import com.footprints.businessservice.domain.board.article.dto.SearchCondition;
import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.entity.LikedArticle;
import com.footprints.businessservice.domain.board.article.exception.ArticleException;
import com.footprints.businessservice.domain.board.article.exception.ArticleExceptionType;
import com.footprints.businessservice.domain.board.article.repository.ArticleRepository;
import com.footprints.businessservice.domain.board.article.repository.LikedArticleRepository;
import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.domain.board.comment.entity.Comment;
import com.footprints.businessservice.domain.board.transfer.dto.TransferDto;
import com.footprints.businessservice.domain.board.transfer.entity.Transfer;
import com.footprints.businessservice.domain.board.transfer.repository.TransferRepository;
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
    private final TransferRepository transferRepository;
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
        List<Comment> comments = articleRepository.getCommentList(articleId);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleExceptionType.NOT_FOUND_ARTICLE));

        article.updateHits();

        List<CommentDto> result = comments.stream()
                .map(comment -> new CommentDto(comment))
                .collect(Collectors.toList());

        if (article.getCategory().equals("transfer")) {
            Transfer transfer = transferRepository.getTransferByArticleId(articleId);
            TransferDto transferDto = TransferDto.builder()
                    .transferId(transfer.getId())
                    .roomType(transfer.getRoomType())
                    .buildingType(transfer.getBuildingType())
                    .contractType(transfer.getContractType())
                    .deposit(transfer.getDeposit())
                    .rent(transfer.getRent())
                    .startDate(transfer.getStartDate())
                    .endDate(transfer.getEndDate())
                    .buildingNumber(transfer.getBuildingNumber())
                    .unitNumber(transfer.getUnitNumber())
                    .supplyArea(transfer.getSupplyArea())
                    .leasableArea(transfer.getLeasableArea())
                    .roomSize(transfer.getRoomSize())
                    .totalFloor(transfer.getTotalFloor())
                    .floor(transfer.getFloor())
                    .heatingType(transfer.getHeatingType())
                    .elevator(transfer.getElevator())
                    .parking(transfer.getParking())
                    .option(transfer.getOption())
                    .build();

            return new ArticleDto(article, result, transferDto);
        }

        return new ArticleDto(article, result);
    }

    @Override
    @Transactional
    public void likeArticle(String token, Long articleId) {
        Long memberId = tokenDecoder.extractMember(token);
        if (findLikedArticleWithMemberIdAndArticleId(memberId, articleId) != null) {
            throw new ArticleException(ArticleExceptionType.ALREADY_LIKED_ARTICLE);
        }

        Article article = updateLikeCount(articleId, 1);

        LikedArticle likedArticle = LikedArticle.builder()
                .article(article)
                .memberId(memberId)
                .build();

        likedArticleRepository.save(likedArticle);
    }

    @Override
    @Transactional
    public void unlikeArticle(String token, Long articleId) {
        Long memberId = tokenDecoder.extractMember(token);
        if (findLikedArticleWithMemberIdAndArticleId(memberId, articleId) == null) {
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
