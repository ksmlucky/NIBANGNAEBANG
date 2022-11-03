package com.footprints.businessservice.app.domain.board.article.repository;

import com.footprints.businessservice.app.domain.board.article.entity.LikedArticle;
import com.footprints.businessservice.app.domain.board.article.repository.custom.LikedArticleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedArticleRepository extends JpaRepository<LikedArticle, Long>, LikedArticleRepositoryCustom {
}
