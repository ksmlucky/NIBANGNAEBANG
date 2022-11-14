package com.footprints.businessservice.app.domain.board.article.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.footprints.businessservice.app.domain.board.transfer.dto.TransferRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CommonRequest {

    private ArticleRequest articleRequest;

    private TransferRequest transferRequest;
    public CommonRequest(ArticleRequest articleRequest, TransferRequest transferRequest) {
        this.articleRequest = articleRequest;
        this.transferRequest = transferRequest;
    }
}
