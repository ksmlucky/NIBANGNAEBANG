package com.footprints.businessservice.app.domain.chat.api;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.app.domain.chat.service.ChatService;
import com.footprints.businessservice.global.common.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatRoomController {

    private final ChatService chatService;

    @Operation(summary = "채팅방 전체 조회")
    @GetMapping("/rooms")
    public ResponseEntity<? extends DataResponse> getRooms() {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(chatService.findAllRooms()));
    }

    @Operation(summary = "채팅방 생성")
    @PostMapping("/room")
    public ResponseEntity<? extends DataResponse> createRoom(@RequestHeader("X-Authorization-Id") String memberId,
                                                             @RequestBody ChatRoomRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(chatService.createChatRoom(request)));
    }

    @Operation(summary = "채팅방 정보 조회")
    @GetMapping("/room/{room-id}")
    public ResponseEntity<? extends DataResponse> getRoomInfo(@PathVariable("room-id") String roomId) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(chatService.findRoomById(roomId)));
    }

    @Operation(summary = "회원별 방 조회")
    @GetMapping("/member")
    public ResponseEntity<? extends DataResponse> getRoomsByMember(@RequestHeader("X-Authorization-Id") String memberId) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(chatService.getMemberEnterRooms(memberId)));
    }
}
