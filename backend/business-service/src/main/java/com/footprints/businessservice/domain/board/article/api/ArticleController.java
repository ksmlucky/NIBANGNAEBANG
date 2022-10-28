package com.footprints.businessservice.domain.board.article.api;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.dto.ArticleRequest;
import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.domain.board.article.service.ArticleService;
import com.footprints.businessservice.domain.message.entity.Message;
import com.footprints.businessservice.global.common.DataResponse;
import com.footprints.businessservice.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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
    private final Environment env;

    @GetMapping("/health_check")
    public String check() {
        log.info("Server port={}", env.getProperty("local.server.port"));
        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

    @GetMapping
    public ResponseEntity<? extends DataResponse> getArticleList(SortCondition condition, Pageable pageable) {
        List<ArticleDto> response = articleService.getArticleList(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @PostMapping
    public ResponseEntity<? extends MessageResponse> saveArticle(@RequestBody ArticleRequest request) {
        articleService.saveArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }

    @GetMapping("/{article-id}")
    public ResponseEntity<? extends DataResponse> getArticle(@PathVariable(name = "article-id") Long articleId) {
        ArticleDto response = articleService.getArticle(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @PostMapping("/{article-id}/like")
    public ResponseEntity<? extends MessageResponse> likeArticle(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "article-id") Long articleId) {
        articleService.likeArticle(token, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    @PostMapping("/{article-id}/unlike")
    public ResponseEntity<? extends MessageResponse> unlikeArticle(@RequestHeader(name = "Authorization") String token, @PathVariable(name = "article-id") Long articleId) {
        articleService.unlikeArticle(token, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }
}
