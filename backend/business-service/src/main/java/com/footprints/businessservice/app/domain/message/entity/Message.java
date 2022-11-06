package com.footprints.businessservice.app.domain.message.entity;

import com.footprints.businessservice.global.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "send_member")
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    private Long sendMember;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "receive_member")
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    private String receiveMember;

    private Long sendMember;

    private Long receiveMember;

    // 보낸 시간 createdAt

    // 제목
    private String title;

    // 내용
    private String content;

    // 보낸 사람이 삭제했는지
    private boolean isDeletedBySendMember;

    // 받은 사람이 삭제했는지
    private boolean isDeletedByReceiveMember;

    private boolean isRead;

    private String boardClassification;

    public void deleteBySendMember() {
        this.isDeletedBySendMember = true;
    }

    public void deleteByReceiveMember() {
        this.isDeletedByReceiveMember = true;
    }

    public boolean isDeleted() {
        return isDeletedBySendMember() && isDeletedByReceiveMember();
    }

    public void readCheck() {
        this.isRead = true;
    }
}
