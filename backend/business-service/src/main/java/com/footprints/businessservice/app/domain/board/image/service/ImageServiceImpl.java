package com.footprints.businessservice.app.domain.board.image.service;

import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.image.dto.ImageDto;
import com.footprints.businessservice.app.domain.board.image.entity.Image;
import com.footprints.businessservice.app.domain.board.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<ImageDto> getImages(Long articleId) {
        List<Image> images = imageRepository.getImages(articleId);

        List<ImageDto> result = images.stream()
                .map(image -> ImageDto.builder()
                        .id(image.getId())
                        .imageName(image.getFileName())
                        .url(image.getUrl())
                        .size(image.getSize())
                        .build())
                .collect(Collectors.toList());

        return result;
    }
}
