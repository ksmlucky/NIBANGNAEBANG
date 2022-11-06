package com.footprints.businessservice.app.domain.message.repository.custom;

import com.footprints.businessservice.app.domain.message.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRepositoryCustom {
    Page<Message> getAllReceivedMessages(Long sendMember, Pageable pageable);
    Page<Message> getAllSentMessages(Long receiveMember, Pageable pageable);

    Message getMessage(Long messageId);
}
