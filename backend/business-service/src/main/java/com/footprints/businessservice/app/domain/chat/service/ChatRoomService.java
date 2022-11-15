package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomInfoRes;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomReq;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRes;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    /** 채팅방을 생성하는 registerChatRoom 입니다. **/
    void registerChatRoom(String memberId, ChatRoomReq chatRoomReq);
//    /** 유저가 속한 채팅방을 전체 조회하는 findAllChatRoom 입니다. **/
//    List<ChatRoomRes> findAllChatRoom(String memberId);
    /** 채팅방 아이디(roomId)를 이용하여 채팅방 정보를 조회하는 findChatRoomInfoByRoomId 입니다. **/
    List<ChatRoom> findChatRoomListByTitle(String title);
    /** 채팅방 아이디(roomId)를 이용하여 채팅방 정보를 조회하는 findChatRoomInfoByRoomId 입니다. **/
    ChatRoomInfoRes findChatRoomInfoByRoomId(String roomId);
//    /** 유저1과 유저2의 채팅방이 존재하는지 확인하는 findChatRoom 입니다. (true: 존재 O, false: 존재 X) **/
//    String findChatRoom(ChatRoomReq chatRoomReq);
//    /** 채팅방에서 유저 참가 정보를 삭제하는 deleteUserInfo 입니다. **/
//    void deleteUserInfo(String id, String userEmail);
}