package com.test_aws_spring_boot.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Service
public class StorageArchiveService {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(MultipartFile file) {
        try {
            String originalName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "";

            String fileName = Instant.now().getEpochSecond() +
                    String.valueOf(Instant.now().getNano()) +
                    originalName.replace(" ", "_");

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(file.getContentType());

            amazonS3Client.putObject(bucketName, fileName, file.getInputStream(), meta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
