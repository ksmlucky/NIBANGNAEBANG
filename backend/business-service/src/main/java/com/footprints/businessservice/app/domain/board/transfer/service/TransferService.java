package com.footprints.businessservice.app.domain.board.transfer.service;

public interface TransferService {

    void register(String memberId, Long articleId);
    void cancel(String memberId, Long articleId);
    void finish(String memberId, Long articleId);
}
