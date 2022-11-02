package com.footprints.businessservice.domain.board.article.api;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.dto.ArticleRequest;
import com.footprints.businessservice.domain.board.article.dto.SearchCondition;
import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.domain.board.article.service.ArticleService;
import com.footprints.businessservice.global.common.DataResponse;
import com.footprints.businessservice.global.common.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<ArticleDto> response = articleService.getArticleList(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @PostMapping
    @Operation(summary = "게시글 등록")
    public ResponseEntity<? extends MessageResponse> saveArticle(@RequestBody ArticleRequest request) {
        articleService.saveArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }

    @GetMapping("/{article-id}")
    @Operation(summary = "게시글 상세 조회")
    public ResponseEntity<? extends DataResponse> getArticle(@PathVariable(name = "article-id") Long articleId) {
        ArticleDto response = articleService.getArticle(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @PostMapping("/{article-id}/like")
    @Operation(summary = "게시글 좋아요")
    public ResponseEntity<? extends MessageResponse> likeArticle(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "article-id") Long articleId) {
        articleService.likeArticle(token, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @DeleteMapping("/{article-id}/unlike")
    @Operation(summary = "게시글 좋아요 해제")
    public ResponseEntity<? extends MessageResponse> unlikeArticle(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "article-id") Long articleId) {
        articleService.unlikeArticle(token, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @GetMapping("/search")
    @Operation(summary = "게시글 검색")
    public ResponseEntity<? extends DataResponse> searchArticle(SearchCondition condition, Pageable pageable) {
        List<ArticleDto> response = articleService.searchArticle(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }
}
