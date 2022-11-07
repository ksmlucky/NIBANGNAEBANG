package com.footprints.businessservice.app.domain.board.transfer.repository;

import com.footprints.businessservice.app.domain.board.article.repository.support.QuerydslRepositorySupport;
import com.footprints.businessservice.app.domain.board.transfer.entity.QTransfer;
import com.footprints.businessservice.app.domain.board.transfer.entity.Transfer;
import com.footprints.businessservice.app.domain.board.transfer.repository.custom.TransferRepositoryCustom;

import static com.footprints.businessservice.app.domain.board.transfer.entity.QTransfer.transfer;

public class TransferRepositoryImpl extends QuerydslRepositorySupport implements TransferRepositoryCustom {

    public TransferRepositoryImpl() {
        super(Transfer.class);
    }


    @Override
    public Transfer getTransferByArticleId(Long articleId) {
        return selectFrom(transfer)
                .where(transfer.article.id.eq(articleId))
                .fetchOne();
    }
}
