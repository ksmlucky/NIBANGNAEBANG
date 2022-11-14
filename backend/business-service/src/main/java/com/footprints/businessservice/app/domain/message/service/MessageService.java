package com.footprints.businessservice.app.domain.message.service;

import com.footprints.businessservice.app.domain.message.dto.MessageDto;
import com.footprints.businessservice.app.domain.message.dto.MessageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {
    // 한 명의 멤버가 받은 모든 쪽지 불러오기(받은 쪽지함)
    List<MessageDto> getAllReceivedMessages(String receiveMember, Pageable pageable);

    // 쪽지 보내기
    void sendMessage(String sendMember, MessageRequest request);

    // 받은 쪽지 삭제
    void deleteMessageByReceiveMember(String receiveMemberId, Long messageId);

    // 보낸 쪽지함
    List<MessageDto> getAllSentMessages(String receiveMember, Pageable pageable);

    // 보낸 쪽지 삭제
    void deleteMessageBySendMember(String sendMemberId, Long messageId);

    // 쪽지 하나 읽기
    MessageDto getMessage(String memberId, Long messageId);
}
