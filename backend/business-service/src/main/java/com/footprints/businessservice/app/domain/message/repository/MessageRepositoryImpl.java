package com.footprints.businessservice.app.domain.message.repository;

import com.footprints.businessservice.app.domain.board.article.repository.support.QuerydslRepositorySupport;
import com.footprints.businessservice.app.domain.message.entity.Message;
import com.footprints.businessservice.app.domain.message.entity.QMessage;
import com.footprints.businessservice.app.domain.message.repository.custom.MessageRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.footprints.businessservice.app.domain.message.entity.QMessage.message;

public class MessageRepositoryImpl extends QuerydslRepositorySupport implements MessageRepositoryCustom {

    public MessageRepositoryImpl() {
        super(Message.class);
    }


    @Override
    public Page<Message> getAllReceivedMessages(String receiveMember, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(message)
                        .where(message.receiveMember.eq(receiveMember)),
                countQuery -> countQuery
                        .selectFrom(message)
                        .where(message.receiveMember.eq(receiveMember))
        );
    }

    @Override
    public Page<Message> getAllSentMessages(String sendMember, Pageable pageable) {

        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(message)
                        .where(message.sendMember.eq(sendMember)),
                countQuery -> countQuery
                        .selectFrom(message)
                        .where(message.sendMember.eq(sendMember))
        );
    }

    @Override
    public Message getMessage(Long messageId) {
        return selectFrom(message)
                .where(message.id.eq(messageId))
                .fetchOne();
    }
}
