package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomInfoRes;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomReq;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRes;

import java.util.List;

public interface ChatRoomService {
    /** 채팅방을 생성하는 registerChatRoom 입니다. **/
    void registerChatRoom(String memberId, ChatRoomReq chatRoomReq);
    /** 유저가 속한 채팅방을 전체 조회하는 findAllChatRoom 입니다. **/
    List<ChatRoomRes> findAllChatRoomsByMemberId(String memberId);
    /** 채팅방 아이디(roomId)를 이용하여 채팅방 정보를 조회하는 findChatRoomInfoByRoomId 입니다. **/
    List<ChatRoomRes> findChatRoomListByTitle(String title);
    /** 채팅방 아이디(roomId)를 이용하여 채팅방 정보를 조회하는 findChatRoomInfoByRoomId 입니다. (방 참가)**/
    ChatRoomInfoRes enterChatRoom(String memberId, String roomId);

    /**
     * 채팅방 나가기
     **/
    void exitChatRoom(String memberid, String roomId);

    /**
     * 모든 채팅방 조회
     **/
    List<ChatRoomRes> findAllChatRoom();
}