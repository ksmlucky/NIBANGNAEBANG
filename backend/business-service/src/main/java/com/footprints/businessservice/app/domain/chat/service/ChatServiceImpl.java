package com.footprints.businessservice.app.domain.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom.ChatRoomMember;
import com.footprints.businessservice.app.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ChatServiceImpl implements ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public void createRoom(String memberId, ChatRoomRequest request) {
        ChatRoomMember chatRoomMember = ChatRoomMember.builder()
                .id(Long.parseLong(memberId))
                .nickname(memberId) // 후에 수정
                .build();

        List<ChatRoomMember> memberList = new ArrayList<>();
        memberList.add(chatRoomMember);

        ChatRoom chatRoom = ChatRoom.builder()
                .title(request.getTitle())
                .members(memberList)
                .currentUserCount(1)
                .totalUserCount(request.getTotalUserCount())
//                .closingTime(request.getClosingTime())
                .fee(request.getFee())
                .build();

//        chatRoomRepository.save(chatRoom);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
