package com.footprints.businessservice.app.domain.message.dto;

import com.footprints.businessservice.app.domain.message.entity.Message;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private Long sendMember;
    private Long receiveMember;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private boolean isRead;

    // 보낸 사람이 삭제했는지
    private boolean isDeletedBySendMember;

    // 받은 사람이 삭제했는지
    private boolean isDeletedByReceiveMember;

    @QueryProjection
    public MessageDto(Message message) {
        this.id = message.getId();
        this.sendMember = message.getSendMember();
        this.receiveMember = message.getReceiveMember();
        this.createdAt = message.getCreatedAt();
        this.title = message.getTitle();
        this.content = message.getContent();
        this.isRead = message.isRead();
        this.isDeletedBySendMember = message.isDeletedBySendMember();
        this.isDeletedByReceiveMember = message.isDeletedByReceiveMember();
    }
}
