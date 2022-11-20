package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatMessageRes;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomInfoRes;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomReq;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRes;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom.ChatRoomMember;
import com.footprints.businessservice.app.domain.chat.exception.ChatException;
import com.footprints.businessservice.app.domain.chat.exception.ChatExceptionType;
import com.footprints.businessservice.app.domain.chat.repository.ChatRoomRepository;
import com.footprints.businessservice.app.domain.member.MemberServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service("ChatRoomService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private final MemberServiceClient memberServiceClient;


    /** 채팅방을 생성하는 registerChatRoom 입니다. **/
    @Override
    @Transactional
    public void registerChatRoom(String memberId, ChatRoomReq chatRoomReq) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        ChatRoom chatRoom = ChatRoom.create(memberId, nickname, chatRoomReq);
        chatRoom.getMembers().get(0).setBangjang(true);

        chatRoomRepository.save(chatRoom);
    }

    /** 유저가 속한 채팅방을 전체 조회하는 findAllChatRoom 입니다. **/
    @Override
    public List<ChatRoomRes> findAllChatRoomsByMemberId(String memberId) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();

        chatRooms = mongoTemplate.find(
                Query.query(Criteria.where("members").elemMatch(
                                Criteria.where("nickname").is(nickname)
                        )
                ),
                ChatRoom.class);

        List<ChatRoomRes> result = chatRooms.stream()
                .map(ChatRoomRes::new)
                .collect(Collectors.toList());

        return result;
    }

    /** 채팅방 title를 이용하여 채팅방 리스트를 조회하는 findChatRoomInfoByTitle 입니다. **/
    @Override
    public List<ChatRoomRes> findChatRoomListByTitle(String title) {
        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRooms = mongoTemplate.find(
                Query.query(Criteria.where("title").is(title)),
                ChatRoom.class
        );

        List<ChatRoomRes> result = chatRooms.stream()
                .map(ChatRoomRes::new)
                .collect(Collectors.toList());

        return result;
    }

    /** 채팅방 아이디(roomId)를 이용하여 채팅방 정보를 조회하는 findChatRoomInfoByRoomId 입니다. (채팅방 들어가기) **/
    @Override
    public ChatRoomInfoRes enterChatRoom(String memberId, String roomId) {
        ChatRoom chatRoom = mongoTemplate.findOne(
                Query.query(Criteria.where("_id").is(roomId)),
                ChatRoom.class
        );

        // 방이 꽉찼으면 입장 불가
        if (chatRoom.getTotalMemberCount() <= chatRoom.getCurrentMemberCount()) {
            throw new ChatException(ChatExceptionType.FULL_CHATROOM);
        }

        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();

        // 방에 해당 이름이 있으면 입장 불가
        for (ChatRoomMember temp : chatRoom.getMembers()) {
            if (temp.getNickname().equals(nickname)) {
                throw new ChatException(ChatExceptionType.ALREADY_EXIST_MEMBER);
            }
        }

        // 없으면 입장
        ChatRoomMember chatRoomMember = new ChatRoomMember();
        chatRoomMember.setId(Long.parseLong(memberId));
        chatRoomMember.setNickname(nickname);

        chatRoom.getMembers().add(chatRoomMember);
        chatRoom.setCurrentMemberCount(chatRoom.getCurrentMemberCount() + 1);

        List<ChatMessageRes> chatMessageResList = new ArrayList<ChatMessageRes>();
        for (int i = 0; i < chatRoom.getChatMessages().size(); i++) {

            ChatMessageRes chatMessageRes = ChatMessageRes.builder()
                    .id(chatRoom.getChatMessages().get(i).getId())
                    .senderNickname(chatRoom.getChatMessages().get(i).getSender())
                    .message(chatRoom.getChatMessages().get(i).getMessage())
                    .type(chatRoom.getChatMessages().get(i).getType())
                    .createdAt(chatRoom.getChatMessages().get(i).getCreatedAt())
                    .build();

            chatMessageResList.add(chatMessageRes);
        }

        chatRoomRepository.save(chatRoom);

        ChatRoomInfoRes chatRoomInfoRes = ChatRoomInfoRes.builder()
                .id(chatRoom.getId())
                .title(chatRoom.getTitle())
                .members(chatRoom.getMembers())
                .chatMessages(chatMessageResList)
                .currentMemberCount(chatRoom.getCurrentMemberCount())
                .totalMemberCount(chatRoom.getTotalMemberCount())
                .fee(chatRoom.getFee())
                .build();

        return chatRoomInfoRes;
    }

    @Override
    public void exitChatRoom(String memberid, String roomId) {
        ChatRoom chatRoom = mongoTemplate.findOne(
                Query.query(Criteria.where("_id").is(roomId)),
                ChatRoom.class
        );


        List<ChatRoomMember> chatRoomMembers = chatRoom.getMembers();
        for (ChatRoomMember chatRoomMember : chatRoomMembers) {
            if (chatRoomMember.getId() == Long.parseLong(memberid)) {
                chatRoomMembers.remove(chatRoomMember);
                break;
            }
        }

        log.info("chatRoomMembers: {}", chatRoomMembers);

        chatRoom.updateMember(chatRoomMembers);

        chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoomRes> findAllChatRoom() {
        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRooms = mongoTemplate.findAll(ChatRoom.class);

        List<ChatRoomRes> result = chatRooms.stream()
                .map(ChatRoomRes::new)
                .collect(Collectors.toList());

        return result;
    }
}
