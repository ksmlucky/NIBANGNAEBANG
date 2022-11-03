package com.footprints.businessservice.app.domain.board.article.dto;

import com.footprints.businessservice.app.domain.board.transfer.dto.TransferRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonRequest {

    private ArticleRequest articleRequest;

    private TransferRequest transferRequest;

}
