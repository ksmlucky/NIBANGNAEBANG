package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatMessageRes;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomInfoRes;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomReq;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRes;
import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import com.footprints.businessservice.app.domain.chat.entity.ChatRoom.ChatRoomMember;
import com.footprints.businessservice.app.domain.chat.repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service("ChatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    /** 채팅방을 생성하는 registerChatRoom 입니다. **/
    @Override
    public void registerChatRoom(String memberId, ChatRoomReq chatRoomReq) {
//        User user1 = userRepositorySupport.findUserByUserNickname(chatRoomReq.getUserNickname1()).orElse(null);
//        User user2 = userRepositorySupport.findUserByUserNickname(chatRoomReq.getUserNickname2()).orElse(null);
//
//        ChatRoom chatRoom = ChatRoom.create(user1.getUserEmail(), user2.getUserEmail(), chatRoomReq.getBoardNo());

        ChatRoom chatRoom = ChatRoom.create(memberId, chatRoomReq);

        chatRoomRepository.save(chatRoom);

//        return chatRoom.getId();
    }

//    /** 유저가 속한 채팅방을 전체 조회하는 findAllChatRoom 입니다. **/
//    @Override
//    public List<ChatRoomRes> findAllChatRoom(String memberId) {
//
//        // memberId로 닉네임 찾기
//        String nickname = "tempnickname";
//
//        Criteria criteria = new Criteria();
//        criteria.orOperator(Criteria.where("userEmail1").is(userEmail), Criteria.where("userEmail2").is(userEmail));
//        Query query = new Query(criteria);
//
//        List<ChatRoom> chatRoomList = mongoTemplate.find(query, ChatRoom.class);
//
//        List<ChatRoomRes> chatroomResList = new ArrayList<ChatRoomRes>();
//        for(int i=0; i<chatRoomList.size(); i++) {
//            ChatRoom chatRoom = chatRoomList.get(i);
//            User user1 = userRepositorySupport.findUserByUserEmail(chatRoom.getUserEmail1()).orElse(null);
//            User user2 = userRepositorySupport.findUserByUserEmail(chatRoom.getUserEmail2()).orElse(null);
//
////            List<ChatMessageRes> chatMessageResList = new ArrayList<ChatMessageRes>();
////            for(int j=0; j<chatRoom.getChatMessages().size(); j++) {
////                String userNickname = "";
////                String userImage = "";
////                if(chatRoom.getChatMessages().get(j).getSender().equals(user1.getUserEmail())) {
////                    userNickname = user1.getUserNickname();
////                    userImage = user1.getUserImage();
////                } else {
////                    userNickname = user2.getUserNickname();
////                    userImage = user2.getUserImage();
////
////                }
////                ChatMessageRes chatMessageRes = ChatMessageRes.builder()
////                        .id(chatRoom.getChatMessages().get(j).getId())
////                        .senderNickname(userNickname)
////                        .senderImage(userImage)
////                        .message(chatRoom.getChatMessages().get(j).getMessage())
////                        .createdAt(chatRoom.getChatMessages().get(j).getCreatedAt())
////                        .build();
////                chatMessageResList.add(chatMessageRes);
////            }
//
//            int size = chatRoomList.get(i).getChatMessages().size();
//            ChatMessage chatMessage = null;
//            if(size > 0) {
//                chatMessage = chatRoomList.get(i).getChatMessages().get(size-1);
//            }
//
//            String userNickname1 = "", userNickname2 = "", userImage1 = "", userImage2 = "";
//            if(user1 != null) {
//                userNickname1 = user1.getUserNickname();
//                userImage1 = user1.getUserImage();
//            }
//            if(user2 != null) {
//                userNickname2 = user2.getUserNickname();
//                userImage2 = user2.getUserImage();
//            }
//
//            List<UserIngredient> userIngredientList = userIngredientRepositorySupport.findByBoardNo(chatRoomList.get(i).getBoardNo());
//            List<String> ingredientList = new ArrayList<String>();
//
//            for(int j=0; j<userIngredientList.size(); j++) {
//                ingredientList.add(userIngredientList.get(j).getIngredient().getIngredientName());
//            }
//
//            ChatRoomRes chatRoomRes=  ChatRoomRes.builder()
//                    .id(chatRoomList.get(i).getId())
//                    .userNickname1(userNickname1)
//                    .userNickname2(userNickname2)
//                    .userImage1(userImage1)
//                    .userImage2(userImage2)
//                    .boardNo(chatRoomList.get(i).getBoardNo())
////                    .chatMessages(chatMessageResList)
//                    .chatMessages(chatMessage)
//                    .ingredientList(ingredientList)
//                    .build();
//            chatroomResList.add(chatRoomRes);
//        }
//        return chatroomResList;
//    }
//
    /** 채팅방 title를 이용하여 채팅방 리스트를 조회하는 findChatRoomInfoByTitle 입니다. **/
    @Override
    public List<ChatRoom> findChatRoomListByTitle(String title) {
        List<ChatRoom> chatRooms = new ArrayList<>();
        chatRooms = mongoTemplate.find(
                Query.query(Criteria.where("title").is(title)),
                ChatRoom.class
        );
//        List<ChatMessageRes> chatMessageResList = new ArrayList<ChatMessageRes>();
//        for (int i = 0; i < chatRooms.size(); i++) {
//            ChatRoom tempChatRoom = chatRooms.get(i);
//
//            ChatMessageRes chatMessageRes = ChatMessageRes.builder()
//                    .id(tempChatRoom.getChatMessages().get(i).getId())
//                    .senderNickname(tempChatRoom.getChatMessages().get(i).getSender())
//                    .message(tempChatRoom.getChatMessages().get(i).getMessage())
//                    .type(tempChatRoom.getChatMessages().get(i).getType())
//                    .createdAt(tempChatRoom.getChatMessages().get(i).getCreatedAt())
//                    .build();
//
//            chatMessageResList.add(chatMessageRes);
//        }
//
//        ChatRoomInfoRes chatRoomInfoRes = ChatRoomInfoRes.builder()
//                .id(chatRoom.getId())
//                .members(chatRoom.getMembers())
//                .chatMessages(chatMessageResList)
//                .build();

//        return chatRooms.stream()
//                .map(id -> new ChatRoomInfoRes(id))
//                .collect(Collectors.toList());

        return chatRooms;
    }

    /** 채팅방 아이디(roomId)를 이용하여 채팅방 정보를 조회하는 findChatRoomInfoByRoomId 입니다. **/
    @Override
    public ChatRoomInfoRes findChatRoomInfoByRoomId(String roomId) {
        ChatRoom chatRoom = mongoTemplate.findOne(
                Query.query(Criteria.where("_id").is(roomId)),
                ChatRoom.class
        );



        log.info("roomId1: {}", roomId);
        log.info("chatRoom: {}", chatRoom);
        List<ChatMessageRes> chatMessageResList = new ArrayList<ChatMessageRes>();
        log.info("roomId2: {}", roomId);
        for (int i = 0; i < chatRoom.getChatMessages().size(); i++) {
//            User user = userRepositorySupport.findUserByUserEmail(chatRoom.getChatMessages().get(i).getSender()).orElse(null);
            log.info("roomId3: {}", roomId);

            ChatMessageRes chatMessageRes = ChatMessageRes.builder()
                    .id(chatRoom.getChatMessages().get(i).getId())
                    .senderNickname(chatRoom.getChatMessages().get(i).getSender())
                    .message(chatRoom.getChatMessages().get(i).getMessage())
                    .type(chatRoom.getChatMessages().get(i).getType())
                    .createdAt(chatRoom.getChatMessages().get(i).getCreatedAt())
                    .build();

            chatMessageResList.add(chatMessageRes);
        }

        ChatRoomInfoRes chatRoomInfoRes = ChatRoomInfoRes.builder()
                .id(chatRoom.getId())
                .title(chatRoom.getTitle())
                .members(chatRoom.getMembers())
                .chatMessages(chatMessageResList)
                .currentMemberCount(chatRoom.getCurrentMemberCount())
                .totalMemberCount(chatRoom.getTotalMemberCount())
                .closingTime(chatRoom.getClosingTime())
                .fee(chatRoom.getFee())
                .build();

        return chatRoomInfoRes;
    }
//
//    /** 유저1과 유저2의 채팅방이 존재하는지 확인하는 findChatRoom 입니다. (true: 존재 O, false: 존재 X) **/
//    @Override
//    public String findChatRoom(ChatRoomReq chatRoomReq) {
//        User user1 = userRepositorySupport.findUserByUserNickname(chatRoomReq.getUserNickname1()).orElse(null);
//        User user2 = userRepositorySupport.findUserByUserNickname(chatRoomReq.getUserNickname2()).orElse(null);
//
//        Criteria criteria = new Criteria();
//        criteria.orOperator(new Criteria().andOperator(Criteria.where("userEmail1").is(user1.getUserEmail()), Criteria.where("userEmail2").is(user2.getUserEmail()), Criteria.where("boardNo").is(chatRoomReq.getBoardNo()))
//                            , new Criteria().andOperator(Criteria.where("userEmail1").is(user2.getUserEmail()), Criteria.where("userEmail2").is(user1.getUserEmail()), Criteria.where("boardNo").is(chatRoomReq.getBoardNo())));
//        Query query = new Query(criteria);
//        List<ChatRoom> chatRoomList = mongoTemplate.find(query, ChatRoom.class);
//
//        if(chatRoomList.size()==0) return "";    // 유저1과 유저2가 속한 채팅방이 존재 X
//        return chatRoomList.get(0).getId();    // 이미 유저1과 유저2가 속한 채팅방이 존재
//    }
//
//    /** 채팅방에서 유저 참가 정보를 삭제하는 deleteUserInfo 입니다. **/
//    @Override
//    public void deleteUserInfo(String id, String userEmail) {
//        // 채팅방 db에 유저 퇴장 메시지 저장
//        User user = userRepositorySupport.findUserByUserEmail(userEmail).orElse(null);
//        LocalDateTime localDateTime = LocalDateTime.now();
//
//        ChatMessage chatMessage = ChatMessage.create(
//                userEmail,
//                user.getUserNickname() + "님이 퇴장하셨습니다 👋",
//                localDateTime,
//                LEAVE);
//
//        Update update = new Update();
//        update.push("chatMessages", chatMessage);
//        Criteria criteria = Criteria.where("_id").is(id);
//        mongoTemplate.updateFirst(Query.query(criteria), update, "chats");
//
//        // 채팅방에서 유저 정보 삭제
//        ChatRoom chatRoom = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), ChatRoom.class);
//
//        if(chatRoom.getUserEmail1().length()==0 || chatRoom.getUserEmail2().length()==0) { // 이미 다른 유저가 나간 경우, 채팅방을 바로 삭제
//            mongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), ChatRoom.class);
//        } else {    // 아직 채팅방에 유저가 존재하는 경우, 현재 유저 정보만 채팅방에서 삭제
//            if(chatRoom.getUserEmail1().equals(userEmail)) {
//                mongoTemplate.updateFirst(
//                        Query.query(Criteria.where("_id").is(id)),
//                        Update.update("userEmail1", ""),
//                        ChatRoom.class
//                );
//            } else {
//                mongoTemplate.updateFirst(
//                        Query.query(Criteria.where("_id").is(id)),
//                        Update.update("userEmail2", ""),
//                        ChatRoom.class
//                );
//            }
//        }
//    }
}
