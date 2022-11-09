package com.footprints.businessservice.app.domain.chat.repository;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(ChatRoomRequest request) {
        ChatRoom chatRoom = ChatRoom.createRoom(request);

        chatRoomMap.put(chatRoom.getId() + "", chatRoom);
        return chatRoom;
    }
}
