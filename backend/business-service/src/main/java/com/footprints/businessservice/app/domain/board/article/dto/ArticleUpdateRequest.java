package com.footprints.businessservice.app.domain.board.article.dto;

import com.footprints.businessservice.app.domain.board.transfer.dto.TransferRequest;
import lombok.Data;

import java.util.List;

@Data
public class ArticleUpdateRequest {

    private String title;

    private String content;

    private List<Long> images;

    private TransferRequest transfer;
}
