package com.footprints.businessservice.app.domain.board.reply.api;

import com.footprints.businessservice.app.domain.board.reply.dto.ReplyRequest;
import com.footprints.businessservice.app.domain.board.reply.dto.ReplyUpdateRequest;
import com.footprints.businessservice.app.domain.board.reply.dto.ReplyDto;
import com.footprints.businessservice.app.domain.board.reply.service.ReplyService;
import com.footprints.businessservice.global.common.DataResponse;
import com.footprints.businessservice.global.common.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/reply")
@Slf4j
public class ReplyController {
    private final ReplyService replyService;
    private final Environment env;

    @GetMapping("/health_check")
    public String check() {
        log.info("Server port={}", env.getProperty("local.server.port"));
        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

    // 대댓글 조회
    @GetMapping("/{comment-id}")
    @Operation(summary = "대댓글 목록 조회")
    public ResponseEntity<? extends DataResponse> getReplyList(@PathVariable("comment-id") Long commentId, Pageable pageable) {
        List<ReplyDto> list = replyService.getReplyList(commentId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(list));
    }

    // 대댓글 등록
    @PostMapping("/{comment-id}")
    @Operation(summary = "대댓글 등록")
    public ResponseEntity<? extends MessageResponse> saveReply(@RequestBody ReplyRequest request, @PathVariable("comment-id") Long commentId) {
        replyService.saveReply(request, commentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }

    // 대댓글 수정
    @PutMapping("{reply-id}")
    @Operation(summary = "대댓글 수정")
    public ResponseEntity<? extends MessageResponse> updateReply(@RequestBody ReplyUpdateRequest request, @PathVariable("reply-id") Long replyId) {
        replyService.updateReply(request, replyId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    // 대댓글 삭제
    @DeleteMapping("{reply-id}")
    @Operation(summary = "대댓글 삭제")
    public ResponseEntity<? extends MessageResponse> deleteReply(@RequestHeader(name = "X-Authorization-Id") String memberId, @PathVariable(name = "reply-id") Long replyId) {
        replyService.deleteReply(memberId, replyId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }
}
