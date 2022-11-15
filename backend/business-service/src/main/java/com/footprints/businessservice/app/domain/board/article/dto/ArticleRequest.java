package com.footprints.businessservice.app.domain.board.article.dto;

import com.footprints.businessservice.app.domain.board.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    private String title;

    private String content;

    private String category;

    public Article toEntity(String nickname) {
        return Article.builder()
                .title(title)
                .content(content)
                .category(category)
                .likes(0)
                .hits(0)
                .writer(nickname)
                .build();
    }
}
