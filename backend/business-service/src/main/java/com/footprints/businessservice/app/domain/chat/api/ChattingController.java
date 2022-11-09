package com.footprints.businessservice.app.domain.chat.api;

import com.footprints.businessservice.app.domain.chat.dto.ChatMessageDto;
import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.service.ChatService;
import com.footprints.businessservice.app.domain.chat.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ChattingController {

    private final RedisPublisher redisPublisher;
    private final ChatService chatService;

    @MessageMapping("/chat/meesage")
    public void message(ChatMessageDto messageDto, @RequestHeader("X-Authorization-Id") String memberId) {
        ChatRoom chatRoom = chatService.findRoomById(messageDto.getRoomId());

        // member 이름 조회해서 넣기 (대화명 설정)
        ChatMessage message = ChatMessage.createChatMessage(chatRoom, "회원 이름", messageDto.getMessage(), messageDto.getType());
        log.info("채팅 메시지");
        if (ChatMessage.MessageType.ENTER.equals(messageDto.getType())) {
            message.updateSender("[알림]");
            message.updateMessage("회원 이름" + "님이 입장하셨습니다.");
        } else if (ChatMessage.MessageType.QUIT.equals(message.getType())) {
            message.updateSender("[알림]");
            message.updateMessage("회원 이름" + "님이 퇴장하셨습니다.");
            chatService.deleteById(message.getChatRoom());
        }

        chatRoom.addChatMessages(message);

        // WebSocket 에 발행된 메시지를 redis 로 발행(publish)
        redisPublisher.publish(chatService.getTopic(messageDto.getRoomId()), message);
    }
}
