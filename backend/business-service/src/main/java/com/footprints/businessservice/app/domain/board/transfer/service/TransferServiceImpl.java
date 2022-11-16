package com.footprints.businessservice.app.domain.board.transfer.service;

import com.footprints.businessservice.app.domain.board.transfer.entity.Transfer;
import com.footprints.businessservice.app.domain.board.transfer.entity.TransferStatus;
import com.footprints.businessservice.app.domain.board.transfer.exception.TransferException;
import com.footprints.businessservice.app.domain.board.transfer.exception.TransferExceptionType;
import com.footprints.businessservice.app.domain.board.transfer.repository.TransferRepository;
import com.footprints.businessservice.app.domain.member.MemberServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final MemberServiceClient memberServiceClient;

    @Override
    @Transactional
    public void register(String memberId, Long articleId) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();
        if (getTransferByNicknameAndArticleId(nickname, articleId) != null) {
            throw new TransferException(TransferExceptionType.ALREADY_REGISTER_ARTICLE);
        }

        Transfer transfer = transferRepository.getTransferByArticleId(articleId);

        if (transfer.getArticle().getWriter().equals(nickname)) {
            throw new TransferException(TransferExceptionType.CANNOT_REGISTER);
        }

        if (transfer.getTransferStatus() != TransferStatus.READY) {
            throw new TransferException(TransferExceptionType.IS_NOT_READY_ARTICLE);
        }

        transfer.updateTenant(nickname);
        transfer.updateStatus(TransferStatus.ONGOING);
    }

    @Override
    @Transactional
    public void cancel(String memberId, Long articleId) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();
        Transfer transfer = getTransferByNicknameAndArticleId(nickname, articleId);

        if (transfer == null || transfer.getTransferStatus() != TransferStatus.ONGOING) {
            throw new TransferException(TransferExceptionType.IS_NOT_REGISTER);
        }

        transfer.updateTenant(null);
        transfer.updateStatus(TransferStatus.READY);
    }

    @Override
    public void finish(String memberId, Long articleId) {
        String nickname = memberServiceClient.selectNickname(Long.parseLong(memberId)).getNickname();
        Transfer transfer = getTransferByNicknameAndArticleId(nickname, articleId);

        if (transfer == null || transfer.getTransferStatus() != TransferStatus.ONGOING) {
            throw new TransferException(TransferExceptionType.IS_NOT_REGISTER);
        }

        if (transfer.getTransferStatus() == TransferStatus.COMPLETE) {
            throw new TransferException(TransferExceptionType.ALREADY_FINISH_TRANSFER);
        }

        transfer.updateStatus(TransferStatus.COMPLETE);
    }

    private Transfer getTransferByNicknameAndArticleId(String nickname, Long articleId) {
        return transferRepository.getTransferByNicknameAndArticleId(nickname, articleId);
    }
}
