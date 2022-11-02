package com.footprints.businessservice.domain.board.article.repository;

import com.footprints.businessservice.domain.board.article.entity.ScrappedArticle;
import com.footprints.businessservice.domain.board.article.repository.custom.ScrappedArticleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrappedArticleRepository extends JpaRepository<ScrappedArticle, Long>, ScrappedArticleRepositoryCustom {

}
