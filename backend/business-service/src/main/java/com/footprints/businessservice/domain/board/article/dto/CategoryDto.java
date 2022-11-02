package com.footprints.businessservice.domain.board.article.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.footprints.businessservice.domain.board.room.dto.RoomDto;
import com.footprints.businessservice.domain.board.transfer.dto.TransferDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private TransferDto transferDto;

    private RoomDto roomDto;

    public CategoryDto(TransferDto transferDto) {
        this.transferDto = transferDto;
    }

    public CategoryDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }
}
