package com.footprints.businessservice.app.domain.board.article.repository.custom;

import com.footprints.businessservice.app.domain.board.article.dto.SearchCondition;
import com.footprints.businessservice.app.domain.board.article.dto.SortCondition;
import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.comment.entity.Comment;
import com.footprints.businessservice.app.domain.board.transfer.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom {
    Page<Article> getArticleList(SortCondition condition, Pageable pageable);
    Page<Transfer> searchArticleList(SortCondition condition, Pageable pageable);
    List<Comment> getCommentList(Long articleId);
    Page<Article> searchArticle(SearchCondition condition, Pageable pageable);
    Article getArticle(Long articleId);
    Article getArticleWithCommentList(Long articleId);
}
