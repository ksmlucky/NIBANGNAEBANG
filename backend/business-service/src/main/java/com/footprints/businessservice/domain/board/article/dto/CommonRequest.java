package com.footprints.businessservice.domain.board.article.dto;

import com.footprints.businessservice.domain.board.transfer.dto.TransferRequest;

public class CommonRequest {

    private ArticleRequest articleRequest;

    private TransferRequest transferRequest;

//    private RoomRequest roomRequest;

    public CommonRequest(ArticleRequest articleRequest, TransferRequest transferRequest) {
        this.articleRequest = articleRequest;

    }

//    public CommonRequest(ArticleRequest articleRequest, RoomRequest roomRequest) {
//
//    }
}
