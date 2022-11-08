package com.footprints.businessservice.app.domain.chat.api;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public void createRoom(@RequestHeader(name = "X-Authorization-Id") String memberId, @RequestBody ChatRoomRequest request) {
        chatService.createRoom(memberId, request);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}