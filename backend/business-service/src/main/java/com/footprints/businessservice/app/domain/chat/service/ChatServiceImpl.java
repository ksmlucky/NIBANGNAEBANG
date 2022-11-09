package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRequest;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatServiceImpl implements ChatService {

    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;
    private static final String CHAT_ROOMS = "CHAT_ROOM";
    public static final String ENTER_INFO = "ENTER_INFO";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    private Map<String, ChannelTopic> topics;
    private HashOperations<String, String, String> hashOpsEnterInfo;

    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        hashOpsEnterInfo = redisTemplate.opsForHash();

        topics = new HashMap<>();
    }

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public List<ChatRoom> findAllRooms() {
        return chatRoomRepository.findAll();
    }

    @Override
    public ChatRoom findRoomById(String id) {
        return chatRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("예외 처리하기"));
    }

    @Override
    @Transactional
    public ChatRoom createChatRoom(ChatRoomRequest request) {
        ChatRoom chatRoom = ChatRoom.createChatRoom(request.getTitle(), request);
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getId(), chatRoom);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    @Override
    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);

        if (topic == null) {
            topic = new ChannelTopic(roomId);
        }

        redisMessageListener.addMessageListener(redisSubscriber, topic);
        topics.put(roomId, topic);
    }

    @Override
    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

    @Override
    public List<ChatRoom> getMemberEnterRooms(String memberId) {
        return chatRoomRepository.findChatRoomsByMembers(Long.parseLong(memberId));
    }

    @Override
    @Transactional
    public void deleteById(ChatRoom chatRoom) {
        chatRoomRepository.delete(chatRoom);
    }

    public void setMemberEnterInfo(String sessionId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
    }

    public String getMemberEnterRoomId(String sessionId) {
        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
    }

    public void removeMemberEnterInfo(String sessionId) {
        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
    }
}
