package com.footprints.businessservice.app.domain.board.transfer.api;

import com.footprints.businessservice.app.domain.board.transfer.service.TransferService;
import com.footprints.businessservice.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Geun
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/AT/{article-id}/register")
    public ResponseEntity<? extends MessageResponse> register(
            @RequestHeader("X-Authorization-Id") String memberId, @PathVariable("article-id") Long articleId) {
        transferService.register(memberId, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse<>());
    }

    @PostMapping("/AT/{article-id}/cancel")
    public ResponseEntity<? extends MessageResponse> cancel(
            @RequestHeader("X-Authorization-Id") String memberId, @PathVariable("article-id") Long articleId) {
        transferService.cancel(memberId, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse<>());
    }

    @PostMapping("/AT/{article-id}/finish")
    public ResponseEntity<? extends MessageResponse> finish(
            @RequestHeader("X-Authorization-Id") String memberId, @PathVariable("article-id") Long articleId) {
        transferService.finish(memberId, articleId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse<>());
    }
}
