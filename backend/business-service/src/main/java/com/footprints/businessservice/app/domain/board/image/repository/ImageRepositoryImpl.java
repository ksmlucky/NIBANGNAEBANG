package com.footprints.businessservice.app.domain.board.image.repository;

import com.footprints.businessservice.app.domain.board.article.repository.support.QuerydslRepositorySupport;
import com.footprints.businessservice.app.domain.board.image.entity.Image;
import com.footprints.businessservice.app.domain.board.image.repository.custom.ImageRepositoryCustom;

import java.util.List;

import static com.footprints.businessservice.app.domain.board.image.entity.QImage.image;

public class ImageRepositoryImpl extends QuerydslRepositorySupport implements ImageRepositoryCustom {
    public ImageRepositoryImpl() {
        super(Image.class);
    }


    @Override
    public List<Image> getImages(Long articleId) {
        return selectFrom(image)
                .where(image.article.id.eq(articleId))
                .fetch();
    }
}
