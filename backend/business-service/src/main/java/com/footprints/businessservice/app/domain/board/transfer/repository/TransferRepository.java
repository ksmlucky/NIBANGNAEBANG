package com.footprints.businessservice.app.domain.board.transfer.repository;

import com.footprints.businessservice.app.domain.board.transfer.repository.custom.TransferRepositoryCustom;
import com.footprints.businessservice.app.domain.board.transfer.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long>, TransferRepositoryCustom {
}
