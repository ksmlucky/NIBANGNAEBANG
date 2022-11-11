package com.footprints.businessservice.app.domain.board.transfer.repository.custom;

import com.footprints.businessservice.app.domain.board.transfer.entity.Transfer;

public interface TransferRepositoryCustom {
    Transfer getTransferByArticleId(Long articleId);
    Transfer searchTransfer(Long articleId, String address);
}
