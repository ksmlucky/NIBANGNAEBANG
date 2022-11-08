package com.footprints.businessservice.app.domain.board.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDto {

    private Long id;

    private String imageName;

    private String url;
}
