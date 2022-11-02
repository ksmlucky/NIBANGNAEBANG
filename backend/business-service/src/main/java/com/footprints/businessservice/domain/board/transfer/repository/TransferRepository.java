package com.footprints.businessservice.domain.board.transfer.repository;

import com.footprints.businessservice.domain.board.transfer.entity.Transfer;
import com.footprints.businessservice.domain.board.transfer.repository.custom.TransferRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long>, TransferRepositoryCustom {
}
