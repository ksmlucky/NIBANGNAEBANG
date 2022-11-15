package com.footprints.businessservice.app.domain.chat.repository;

import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}