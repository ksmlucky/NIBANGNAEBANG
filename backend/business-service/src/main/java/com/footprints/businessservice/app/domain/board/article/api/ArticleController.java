package com.footprints.businessservice.app.domain.board.article.api;

import com.footprints.businessservice.app.domain.board.article.dto.*;
import com.footprints.businessservice.app.domain.board.article.exception.ArticleException;
import com.footprints.businessservice.app.domain.board.article.exception.ArticleExceptionType;
import com.footprints.businessservice.app.domain.board.article.service.ArticleService;
import com.footprints.businessservice.global.common.DataResponse;
import com.footprints.businessservice.global.common.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping
    @Operation(summary = "게시글 목록 조회")
    public ResponseEntity<? extends DataResponse> getArticleList(SortCondition condition, Pageable pageable) {
        ArticleResponse response = articleService.getArticleList(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @PostMapping
    @Operation(summary = "게시글 등록")
    public ResponseEntity<? extends MessageResponse> saveArticle(
            @RequestHeader(name = "X-Authorization-Id") String memberId,
            @RequestPart(name = "request") CommonRequest request,
            @RequestPart(name = "file", required = false) List<MultipartFile> multipartFiles) {
        if (multipartFiles != null && !checkImageType(multipartFiles)) {
            throw new ArticleException(ArticleExceptionType.FILE_IS_NOT_IMAGE);
        }

        articleService.saveArticle(memberId, request, multipartFiles);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }

    @GetMapping("/{article-id}")
    @Operation(summary = "게시글 상세 조회")
    public ResponseEntity<? extends DataResponse> getArticle(@PathVariable(name = "article-id") Long articleId) {
        ArticleDto response = articleService.getArticle(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }
    @PutMapping("/{article-id}")
    @Operation(summary = "게시글 수정")
    public ResponseEntity<? extends MessageResponse> updateArticle(
            @RequestHeader(name = "X-Authorization-Id") String memberId,
            @PathVariable(name = "article-id") Long articleId,
            @RequestPart(name = "request") ArticleUpdateRequest articleUpdateRequest,
            @RequestPart(name = "file", required = false) List<MultipartFile> multipartFiles) {

        articleService.updateArticle(memberId, articleId, articleUpdateRequest, multipartFiles);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @DeleteMapping("/{article-id}")
    @Operation(summary = "게시글 삭제")
    public ResponseEntity<? extends MessageResponse> deleteArticle(
            @RequestHeader(name = "X-Authorization-Id") String memberId,
            @PathVariable(name = "article-id") Long articleId) {

        articleService.deleteArticle(memberId, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @PostMapping("/{article-id}/like")
    @Operation(summary = "게시글 좋아요")
    public ResponseEntity<? extends MessageResponse> likeArticle(@RequestHeader(name = "X-Authorization-Id") String memberId, @PathVariable(name = "article-id") Long articleId) {
        articleService.likeArticle(memberId, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @DeleteMapping("/{article-id}/unlike")
    @Operation(summary = "게시글 좋아요 해제")
    public ResponseEntity<? extends MessageResponse> unlikeArticle(@RequestHeader(name = "X-Authorization-Id") String memberId, @PathVariable(name = "article-id") Long articleId) {
        articleService.unlikeArticle(memberId, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @GetMapping("/search")
    @Operation(summary = "게시글 검색")
    public ResponseEntity<? extends DataResponse> searchArticle(SearchCondition condition, Pageable pageable) {
        List<ArticleDto> response = articleService.searchArticle(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @PostMapping("/scrap/{article-id}")
    @Operation(summary = "게시글 스크랩")
    public ResponseEntity<? extends MessageResponse> scrapArticle(@RequestHeader(name = "X-Authorization-Id") String memberId, @PathVariable(name = "article-id") Long articleId) {
        articleService.scrapArticle(memberId, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @DeleteMapping("/scrap/{article-id}")
    @Operation(summary = "게시글 스크랩 취소")
    public ResponseEntity<? extends MessageResponse> unscrapArticle(@RequestHeader(name = "X-Authorization-Id") String memberId, @PathVariable(name = "article-id") Long articleId) {
        articleService.unscrapArticle(memberId, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @GetMapping("/scrap/{category}")
    @Operation(summary = "스크랩한 게시글 목록")
    public ResponseEntity<? extends DataResponse> getScrappedArticleList(@RequestHeader(name = "X-Authorization-Id") String memberId, @PathVariable(name = "category") String category, Pageable pageable) {
        List<ScrappedArticleDto> list = articleService.getScrappedArticleList(memberId, category, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(list));
    }

    private boolean checkImageType(List<MultipartFile> multipartFiles) {
        boolean isImage = false;
        try {
            for (MultipartFile file : multipartFiles) {
                isImage = file.getContentType().startsWith("image");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return isImage;
    }
}
