package com.footprints.businessservice.domain.board.comment.api;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.comment.dto.CommentResDto;
import com.footprints.businessservice.domain.board.comment.service.CommentService;
import com.footprints.businessservice.global.common.DataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    private final Environment env;

    @GetMapping("/health_check")
    public String check() {
        log.info("Server port={}", env.getProperty("local.server.port"));
        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

    // 댓글 조회
    @GetMapping
    public ResponseEntity<? extends DataResponse> getCommentList(ArticleDto id) {
//        List<CommentResDto> list = commentService
        return null;
    }

    // 댓글 등록

    // 댓글 수정

}
