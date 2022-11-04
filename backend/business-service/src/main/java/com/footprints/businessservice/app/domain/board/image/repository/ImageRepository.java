package com.footprints.businessservice.app.domain.board.image.repository;

import com.footprints.businessservice.app.domain.board.image.entity.Image;
import com.footprints.businessservice.app.domain.board.image.repository.custom.ImageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {
}
