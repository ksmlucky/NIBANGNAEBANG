package com.footprints.businessservice.app.domain.board.article.dto;

import lombok.Data;

@Data
public class SortCondition {

    private String category;

    private String sorting;

    private String orderBy;

    private String address;
}
