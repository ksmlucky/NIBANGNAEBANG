package com.footprints.businessservice.app.domain.board.image.repository.custom;

import com.footprints.businessservice.app.domain.board.image.entity.Image;

import java.util.List;

public interface ImageRepositoryCustom {

    List<Image> getImages(Long articleId);
}
