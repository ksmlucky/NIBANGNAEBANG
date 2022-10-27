package com.footprints.businessservice.domain.board.article.api;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.domain.board.article.service.ArticleService;
import com.footprints.businessservice.global.common.DataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<ArticleDto> list = articleService.getArticleList(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(list));
    }
}
