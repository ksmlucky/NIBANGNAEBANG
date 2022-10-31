package com.footprints.businessservice.domain.board.article.repository;

import com.footprints.businessservice.domain.board.article.entity.Article;
import com.footprints.businessservice.domain.board.article.repository.custom.ArticleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

}
