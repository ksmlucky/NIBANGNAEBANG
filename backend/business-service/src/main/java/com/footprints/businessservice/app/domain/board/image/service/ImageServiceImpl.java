package com.footprints.businessservice.app.domain.board.image.service;

import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.article.exception.ArticleException;
import com.footprints.businessservice.app.domain.board.article.exception.ArticleExceptionType;
import com.footprints.businessservice.app.domain.board.image.entity.Image;
import com.footprints.businessservice.app.domain.board.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

    private final S3Service s3Service;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public void saveImage(Article article, List<MultipartFile> multipartFiles) {
        multipartFiles.forEach(file -> {
            Map<String, String> fileInfoList = s3Service.uploadFile(file);
            String fileName = fileInfoList.get("name");
            String url = fileInfoList.get("url");

            Image image = Image.builder()
                    .article(article)
                    .fileName(fileName)
                    .originalFileName(file.getOriginalFilename())
                    .size(file.getSize())
                    .url(url)
                    .build();

            imageRepository.save(image);
        });
    }

    @Override
    @Transactional
    public void deleteImage(List<Long> images) {
        images.forEach(imageId -> {
            Image image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new ArticleException(ArticleExceptionType.BAD_REQUEST));

            imageRepository.delete(image);
            s3Service.deleteFile(image.getFileName());
        });
    }
}
