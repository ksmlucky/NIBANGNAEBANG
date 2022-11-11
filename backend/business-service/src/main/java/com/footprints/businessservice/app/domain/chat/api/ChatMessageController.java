package com.footprints.businessservice.app.domain.chat.api;

import com.footprints.businessservice.app.domain.chat.dto.ChatMessageReq;
import com.footprints.businessservice.app.domain.chat.dto.ChatMessageRes;
import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import com.footprints.businessservice.app.domain.chat.service.ChatMessageService;
import com.footprints.businessservice.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RequiredArgsConstructor
@Controller
//@Api(value = "채팅 메시지 API", tags = {"ChatMessage"})
//@RequestMapping("/api/message")
public class ChatMessageController {

//    @Autowired
//    UserRepositorySupport userRepositorySupport;

//    @Autowired
    ChatMessageService chatMessageService;

    private final SimpMessageSendingOperations messagingTemplate;

    /** 메시지 생성 **/
//    @PostMapping("/send")
    @MessageMapping("/chat/message")
//    @ApiOperation(value = "메시지 등록(token)", notes = "<strong>채팅 메시지 정보</strong>를 생성한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
    public ResponseEntity<? extends MessageResponse> sendChatMessage(
            @RequestHeader("X-Authorization-Id") String memberId,
            @RequestBody ChatMessageReq chatMessageReq) {

//        User user = userRepositorySupport.findUserByUserNickname(chatMessageReq.getSender()).orElse(null);
//        String userEmail = user.getUserEmail();
        ChatMessage chatMessage = null;

        if(ChatMessage.MessageType.ENTER.equals(chatMessageReq.getType())) {
            chatMessageReq.setMessage(memberId + "님이 입장하셨습니다.");
        } else if (ChatMessage.MessageType.QUIT.equals(chatMessageReq.getType())) {
            chatMessageReq.setMessage(memberId + "님이 퇴장하셨습니다.");
        } else {
            try {
                chatMessage = chatMessageService.registerChatMessage(chatMessageReq, memberId);  // 메시지 저장
            } catch (Exception E) {
                E.printStackTrace();
                ResponseEntity.status(400).body(new MessageResponse());
            }
        }

        ChatMessageRes chatMessageRes = ChatMessageRes.builder()
                .id(chatMessage.getId())
                .senderNickname("tempnickname")
                .message(chatMessage.getMessage())
                .type(chatMessage.getType())
                .createdAt(chatMessage.getCreatedAt())
                .build();

        messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessageReq.getRoomId(), chatMessageRes);   // 원하는 채팅방에 메시지 정보 전송
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }
}
