package com.footprints.businessservice.domain.board.image.service;

import com.footprints.businessservice.domain.board.image.entity.Image;
import com.footprints.businessservice.domain.board.image.repository.ImageRepository;
import com.footprints.businessservice.domain.board.image.util.ImageStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageStore imageStore;

    @Override
    @Transactional
    public List<Image> saveImage(List<MultipartFile> multipartFileList) throws IOException {
        return imageStore.storeImages(multipartFileList);
    }

    @Override
    public List<Image> loadImage(List<MultipartFile> multipartFileList) throws IOException {
        return imageRepository.findAll();
    }
}
