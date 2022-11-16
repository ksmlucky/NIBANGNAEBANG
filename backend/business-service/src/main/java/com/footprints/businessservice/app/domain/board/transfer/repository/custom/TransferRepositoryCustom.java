package com.footprints.businessservice.app.domain.board.transfer.repository.custom;

import com.footprints.businessservice.app.domain.board.transfer.entity.Transfer;

public interface TransferRepositoryCustom {
    Transfer getTransferByArticleId(Long articleId);
    Transfer getTransferByNicknameAndArticleId(String nickname, Long articleId);
}
