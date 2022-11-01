package com.footprints.businessservice.domain.board.transfer.repository.custom;

import com.footprints.businessservice.domain.board.transfer.entity.Transfer;

public interface TransferRepositoryCustom {
    Transfer getTransferByArticleId(Long articleId);
}
