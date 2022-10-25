package com.footprints.businessservice.domain.transferboard.repository;

import com.footprints.businessservice.domain.transferboard.entity.Transfer;
import com.footprints.businessservice.domain.transferboard.repository.custom.TransferRepositoryCustom;
import com.footprints.businessservice.domain.transferboard.repository.support.QuerydslRepositorySupport;

public class TransferRepositoryImpl extends QuerydslRepositorySupport implements TransferRepositoryCustom {
    public TransferRepositoryImpl() {
        super(Transfer.class);
    }
}
