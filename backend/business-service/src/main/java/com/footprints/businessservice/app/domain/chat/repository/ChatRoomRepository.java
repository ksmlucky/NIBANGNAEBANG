package com.footprints.businessservice.app.domain.chat.repository;

import com.footprints.businessservice.app.domain.chat.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

//    @Query("{$or : [{userEmail : ?0}, {userEmail : ?0}]}")
//    List<ChatRoom> findAllByUserEmail(String userEmail1, String userEmail2);
}
