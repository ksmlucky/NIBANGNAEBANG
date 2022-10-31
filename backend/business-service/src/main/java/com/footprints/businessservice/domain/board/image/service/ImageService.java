package com.footprints.businessservice.domain.board.image.service;

import com.footprints.businessservice.domain.board.image.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ImageService {

    List<Image> saveImage(List<MultipartFile> multipartFileList) throws IOException;
    List<Image> loadImage(List<MultipartFile> multipartFileList) throws IOException;
}
