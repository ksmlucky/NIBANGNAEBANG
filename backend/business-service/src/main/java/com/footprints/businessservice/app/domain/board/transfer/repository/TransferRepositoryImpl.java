package com.footprints.businessservice.app.domain.board.transfer.repository;

import com.footprints.businessservice.app.domain.board.article.repository.support.QuerydslRepositorySupport;
import com.footprints.businessservice.app.domain.board.transfer.entity.Transfer;
import com.footprints.businessservice.app.domain.board.transfer.repository.custom.TransferRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;

import static com.footprints.businessservice.app.domain.board.transfer.entity.QTransfer.transfer;

@Slf4j
public class TransferRepositoryImpl extends QuerydslRepositorySupport implements TransferRepositoryCustom {

    public TransferRepositoryImpl() {
        super(Transfer.class);
    }


    @Override
    public Transfer getTransferByArticleId(Long articleId) {
        return selectFrom(transfer)
                .innerJoin(transfer.article)
                .where(articleEq(articleId))
                .fetchOne();
    }

    private BooleanExpression articleEq(Long articleId) {
        return transfer.article.id.eq(articleId);
    }
}
