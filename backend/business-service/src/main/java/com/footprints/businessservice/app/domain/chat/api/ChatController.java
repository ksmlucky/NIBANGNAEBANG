package com.footprints.businessservice.app.domain.chat.api;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomInfoRes;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomReq;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRes;
import com.footprints.businessservice.app.domain.chat.service.ChatMessageService;
import com.footprints.businessservice.app.domain.chat.service.ChatRoomService;
import com.footprints.businessservice.global.common.DataResponse;
import com.footprints.businessservice.global.common.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    ChatMessageService chatMessageService;

    /**
     * 채팅룸 생성
     **/
    @PostMapping("/AT")
    public ResponseEntity<? extends MessageResponse> registerChatRoom(@RequestHeader("X-Authorization-Id") String memberId,
                                                                      @RequestBody ChatRoomReq chatRoomReq) {

        chatRoomService.registerChatRoom(memberId, chatRoomReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }

    /**
     * (유저의) 채팅방 전체 조회
     **/
    @GetMapping("/AT")
    public ResponseEntity<List<ChatRoomRes>> findAllChatRoomsByMemberId(@RequestHeader("X-Authorization-Id") String memberId) {
        List<ChatRoomRes> chatRoomResList = chatRoomService.findAllChatRoomsByMemberId(memberId);
        return new ResponseEntity<List<ChatRoomRes>>(chatRoomResList, HttpStatus.OK);
    }

    /**
     * 선택한 채팅방 상세 정보 조회(방 들어가기)
     **/
    @GetMapping("/AT/{room-id}")
    public ResponseEntity<? extends DataResponse> enterChatRoom(@RequestHeader("X-Authorization-Id") String memberId, @PathVariable("room-id") String roomId) {
        ChatRoomInfoRes chatRoomInfoRes = null;
        try {
            chatRoomInfoRes = chatRoomService.enterChatRoom(memberId, roomId);
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(chatRoomInfoRes));
    }

    /**
     * 채팅방 리스트(제목으로) 조회
     **/
    @GetMapping("/{title}")
    public ResponseEntity<? extends DataResponse> findChatRoomListByTitle(@PathVariable("title") String title) {
        List<ChatRoomRes> chatRooms = null;
        try {
            chatRooms = chatRoomService.findChatRoomListByTitle(title);
        } catch (Exception E) {
            E.printStackTrace();

        }
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(chatRooms));
    }

    /**
     * 채팅방 리스트 전체 조회
     */
    @GetMapping
    public ResponseEntity<? extends DataResponse> findAllChatRoomList() {
        List<ChatRoomRes> chatRooms = null;
        try {
            chatRooms = chatRoomService.findAllChatRoom();
        } catch (Exception E) {
            E.printStackTrace();

        }
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(chatRooms));
    }


    /**
     * 채팅방 나가기
     **/
    @PutMapping("/AT/{room-id}")
    public ResponseEntity<? extends MessageResponse> exitChatRoom(@RequestHeader("X-Authorization-Id") String memberId, @PathVariable("room-id") String roomId) {
        chatRoomService.exitChatRoom(memberId, roomId);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }
}
