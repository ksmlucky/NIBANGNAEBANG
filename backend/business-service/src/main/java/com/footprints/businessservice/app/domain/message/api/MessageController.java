package com.footprints.businessservice.app.domain.message.api;

import com.footprints.businessservice.app.domain.message.dto.MessageDto;
import com.footprints.businessservice.app.domain.message.dto.MessageRequest;
import com.footprints.businessservice.app.domain.message.service.MessageService;
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
@RequestMapping("/api/message")
@Slf4j
public class MessageController {
    private final MessageService messageService;
    private final Environment env;

    @GetMapping("/health_check")
    public String check() {
        log.info("Server port={}", env.getProperty("local.server.port"));
        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

    // 받은 쪽지 리스트
    @GetMapping("/received")
    @Operation(summary = "받은 쪽지 목록 조회")
    public ResponseEntity<? extends DataResponse> getAllReceivedMessages(@RequestHeader(name = "X-Authorization-Id") String receiveMember, Pageable pageable) {
        List<MessageDto> messageDtos = messageService.getAllReceivedMessages(receiveMember, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(messageDtos));
    }

    // 보낸 쪽지 리스트
    @GetMapping("/sent")
    @Operation(summary = "보낸 쪽지 목록 조회")
    public ResponseEntity<? extends DataResponse> getAllSentMessages(@RequestHeader(name = "X-Authorization-Id") String sendMember, Pageable pageable) {
        List<MessageDto> messageDtos = messageService.getAllSentMessages(sendMember, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(messageDtos));
    }

    // 쪽지 하나 조회
    @GetMapping("/received/{message-id}")
    @Operation(summary = "쪽지 상세 조회")
    public ResponseEntity<? extends DataResponse> getMessage(@PathVariable(name = "message-id") Long messageId) {
        MessageDto messageDto = messageService.getMessage(messageId);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(messageDto));
    }

    // 쪽지 보내기
    @PostMapping
    @Operation(summary = "쪽지 보내기")
    public ResponseEntity<? extends MessageResponse> sendMessage(@RequestHeader(name = "X-Authorization-Id") String sendMember, @RequestBody MessageRequest request) {
        messageService.sendMessage(sendMember, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }

    // 받은 쪽지 삭제
    @DeleteMapping("/received/delete/{message-id}")
    @Operation(summary = "받은 쪽지 삭제")
    public ResponseEntity<? extends MessageResponse> deleteReceivedMessage(@RequestHeader(name = "X-Authorization-Id") String receiveMember, @PathVariable("message-id") Long messageId) {
        messageService.deleteMessageByReceiveMember(receiveMember, messageId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }

    // 보낸 쪽지 삭제
    @DeleteMapping("/sent/delete/{message-id}")
    @Operation(summary = "보낸 쪽지 삭제")
    public ResponseEntity<? extends MessageResponse> deleteSentMessage(@RequestHeader(name = "X-Authorization-Id") String sendMember, @PathVariable("message-id") Long messageId) {
        messageService.deleteMessageBySendMember(sendMember, messageId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }
}
