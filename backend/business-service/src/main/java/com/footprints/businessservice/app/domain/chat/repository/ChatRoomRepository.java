package com.footprints.businessservice.app.domain.chat.repository;

import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    List<ChatRoom> findChatRoomsByMembers(Long memberId);
}
