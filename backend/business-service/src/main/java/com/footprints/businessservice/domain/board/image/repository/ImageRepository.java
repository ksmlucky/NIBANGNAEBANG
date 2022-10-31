package com.footprints.businessservice.domain.board.image.repository;

import com.footprints.businessservice.domain.board.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
