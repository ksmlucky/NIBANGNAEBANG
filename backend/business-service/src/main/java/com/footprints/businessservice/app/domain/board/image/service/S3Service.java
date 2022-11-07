package com.footprints.businessservice.app.domain.board.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public Map<String, String> uploadFile(MultipartFile file) {
        Map<String, String> fileInfoList = new HashMap<>();

        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            uploadThumbnail(file, fileName);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        fileInfoList.put("name", fileName);
        fileInfoList.put("url", amazonS3.getUrl(bucket, fileName).toString());

        return fileInfoList;
    }

    public void uploadThumbnail(MultipartFile file, String fileName) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            BufferedImage thumbnail = Thumbnails.of(bufferedImage).size(400, 300).asBufferedImage();

            ByteArrayOutputStream thumbnailOutput = new ByteArrayOutputStream();
            String fileType = file.getContentType();
            ImageIO.write(thumbnail, fileType.substring(fileType.indexOf("/") + 1), thumbnailOutput);

            ObjectMetadata thumbnailObjectMetadata = new ObjectMetadata();
            byte[] thumbnailBytes = thumbnailOutput.toByteArray();
            thumbnailObjectMetadata.setContentType(file.getContentType());
            thumbnailObjectMetadata.setContentLength(thumbnailBytes.length);

            InputStream thumbnailInput = new ByteArrayInputStream(thumbnailBytes);
            amazonS3.putObject(bucket, "s_" + fileName, thumbnailInput, thumbnailObjectMetadata);

            thumbnailInput.close();
            thumbnailOutput.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, "s_" + fileName));
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}
