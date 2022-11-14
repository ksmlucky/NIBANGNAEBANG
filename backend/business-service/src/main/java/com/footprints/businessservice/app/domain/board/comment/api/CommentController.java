package com.footprints.businessservice.app.domain.board.comment.api;

import com.footprints.businessservice.app.domain.board.comment.dto.CommentDto;
import com.footprints.businessservice.app.domain.board.comment.dto.CommentRequest;
import com.footprints.businessservice.app.domain.board.comment.service.CommentService;
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
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("/{article-id}")
    @Operation(summary = "댓글 목록 조회", description = "전체 댓글 목록을 조회한다.")
    public ResponseEntity<? extends DataResponse> getCommentList(@PathVariable("article-id") Long articleId, Pageable pageable) {
        List<CommentDto> list = commentService.getCommentList(articleId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(list));
    }

    // 댓글 등록
    @PostMapping("/AT/{article-id}")
    @Operation(summary = "댓글 등록")
    public ResponseEntity<? extends MessageResponse> saveComment(@RequestHeader(name = "X-Authorization-Id") String memberId, @RequestBody CommentRequest request, @PathVariable("article-id") Long articleId) {
        commentService.saveComment(memberId, request, articleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }

    // 댓글 수정
    @PutMapping("/AT/{comment-id}")
    @Operation(summary = "댓글 수정")
    public ResponseEntity<? extends MessageResponse> updateComment(@RequestHeader(name = "X-Authorization-Id") String memberId, @RequestBody CommentRequest request, @PathVariable("comment-id") Long commentId) {
        commentService.updateComment(memberId, request, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    // 댓글 삭제
    @DeleteMapping("/AT/{comment-id}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<? extends MessageResponse> deleteComment(@RequestHeader(name = "X-Authorization-Id") String memberId, @PathVariable(name = "comment-id") Long commentId) {
        commentService.deleteComment(memberId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }
}
