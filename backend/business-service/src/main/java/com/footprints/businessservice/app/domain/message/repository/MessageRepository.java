package com.footprints.businessservice.app.domain.message.repository;

import com.footprints.businessservice.app.domain.message.entity.Message;
import com.footprints.businessservice.app.domain.message.repository.custom.MessageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCustom {
}
