package com.footprints.businessservice.domain.board.image.util;

import com.footprints.businessservice.domain.board.image.entity.Image;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageStore {

//    @Value("${file.dir}/")
    private String fileDirPath;

    public List<Image> storeImages(List<MultipartFile> multipartFiles) throws IOException {
        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                images.add(storeImage(multipartFile));
            }
        }

        return images;
    }

    public Image storeImage(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalImageName = multipartFile.getOriginalFilename();
        String storeImageName = createStoreName(originalImageName);
        multipartFile.transferTo(new File(createPath(storeImageName)));

        return Image.builder()
                .originalName(originalImageName)
                .url(storeImageName)
                .size(multipartFile.getSize())
                .build();
    }

    public String createPath(String storeImageName) {
        return fileDirPath + storeImageName;
    }

    public String createStoreName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();

        // 썸네일 이름 처리

        String storeImageName = uuid;

        return storeImageName;
    }
}
