package com.footprints.businessservice.domain.board.article.dto;

import com.footprints.businessservice.domain.board.transfer.dto.TransferRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonRequest {

    private ArticleRequest articleRequest;

    private TransferRequest transferRequest;

}
