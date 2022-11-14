package com.footprints.businessservice.app.domain.board.reply.repository;

import com.footprints.businessservice.app.domain.board.reply.entity.Reply;
import com.footprints.businessservice.app.domain.board.reply.repository.custom.ReplyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyRepositoryCustom {

}
