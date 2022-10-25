package com.footprints.businessservice.domain.transferboard.repository;

import com.footprints.businessservice.domain.transferboard.entity.Transfer;
import com.footprints.businessservice.domain.transferboard.repository.custom.TransferRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long>, TransferRepositoryCustom {
}
