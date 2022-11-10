package com.footprints.businessservice.app.domain.chat.service;

import com.footprints.businessservice.app.domain.chat.dto.ChatMessageReq;
import com.footprints.businessservice.app.domain.chat.entity.ChatMessage;

public interface ChatMessageService {
    /** 채팅 메시지를 생성하는 registerChatMessage 입니다. **/
    ChatMessage registerChatMessage(ChatMessageReq chatMessageReq, String userEmail);
}
