package com.footprints.businessservice.domain.board.article.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.footprints.businessservice.domain.board.room.dto.RoomRequest;
import com.footprints.businessservice.domain.board.transfer.dto.TransferRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CommonRequest {

    private ArticleRequest articleRequest;

    private TransferRequest transferRequest;

    private RoomRequest roomRequest;

    public CommonRequest(ArticleRequest articleRequest, TransferRequest transferRequest) {
        this.articleRequest = articleRequest;
        this.transferRequest = transferRequest;
    }

    public CommonRequest(ArticleRequest articleRequest, RoomRequest roomRequest) {
        this.articleRequest = articleRequest;
        this.roomRequest = roomRequest;
    }
}
