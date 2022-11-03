package com.footprints.businessservice.app.domain.message.entity;

import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Message extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    private String sendMember;

    private String receiveMember;

    // 보낸 시간 createdAt

    // 제목
    private String title;

    // 내용
    private String content;

    private boolean isDeletedBySendMember;

    private boolean isDeletedByReceiveMember;

    private boolean readCheck;

    private String boardClassification;

    public void deleteBySendMember() {
        this.isDeletedBySendMember = true;
    }

    public void deleteByReceiveMember() {
        this.isDeletedByReceiveMember = true;
    }

//    public boolean isDeleted() {
//        return isDeletedB ySendMember() && isDeletedByReceiveMember();
//    }
}
