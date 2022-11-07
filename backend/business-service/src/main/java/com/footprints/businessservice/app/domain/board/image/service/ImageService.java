package com.footprints.businessservice.app.domain.board.image.service;

import com.footprints.businessservice.app.domain.board.article.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    void saveImage(Article article, List<MultipartFile> multipartFiles);
}
