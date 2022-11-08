package com.footprints.businessservice.app.domain.chat.api;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    @Operation(summary = "채팅방 생성")
    public void createRoom(@RequestHeader(name = "X-Authorization-Id") String memberId, @RequestBody ChatRoomRequest request) {
        chatService.createRoom(memberId, request);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}