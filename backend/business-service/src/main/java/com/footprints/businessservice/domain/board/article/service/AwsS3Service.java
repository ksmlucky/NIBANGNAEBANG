package com.footprints.businessservice.domain.board.article.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwsS3Service {

    List<String> uploadFile(List<MultipartFile> multipartFiles);

    void deleteFile(String fileName);
}
