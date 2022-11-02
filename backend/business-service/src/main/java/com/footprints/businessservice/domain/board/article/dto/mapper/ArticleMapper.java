package com.footprints.businessservice.domain.board.article.dto.mapper;

import com.footprints.businessservice.domain.board.article.dto.ArticleDto;
import com.footprints.businessservice.domain.board.article.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public abstract class ArticleMapper {

    @Mapping(target = "hits", ignore = true)
    @Mapping(target = "likes", ignore = true)
    public abstract ArticleDto toArticleDto(Article article);
}
