package com.footprints.businessservice.domain.board.article.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.footprints.businessservice.domain.board.transfer.dto.TransferDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private TransferDto transferDto;

    public CategoryDto(TransferDto transferDto) {
        this.transferDto = transferDto;
    }

}
