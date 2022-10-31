package com.footprints.businessservice.domain.board.comment.api;

import com.footprints.businessservice.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.domain.board.comment.dto.CommentRequest;
import com.footprints.businessservice.domain.board.comment.service.CommentService;
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
    @GetMapping("/{article-id}")
    public ResponseEntity<? extends DataResponse> getCommentList(@PathVariable("article-id") Long articleId, Pageable pageable) {
        List<CommentDto> list = commentService.getCommentList(articleId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(list));
    }

    // 댓글 등록
    @PostMapping("/{article-id}")
    public ResponseEntity<? extends MessageResponse> saveComment(@RequestBody CommentRequest request, @PathVariable("article-id") Long articleId) {
        commentService.saveComment(request, articleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }

    // 댓글 수정

    // 댓글 삭제
//    @DeleteMapping("/{comment-id}")
//    public ResponseEntity<? extends MessageResponse> deleteComment(@PathVariable("comment-id") Long commentId) {
//        commentService.deleteComment(commentId);
//        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
//    }
}
