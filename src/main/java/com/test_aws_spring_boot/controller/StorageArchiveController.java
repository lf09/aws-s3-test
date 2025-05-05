package com.test_aws_spring_boot.controller;

import com.test_aws_spring_boot.dto.FileDTO;
import com.test_aws_spring_boot.service.StorageArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/file_storage")
public class StorageArchiveController {

    @Autowired
    private StorageArchiveService storageArchiveService;

    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        storageArchiveService.uploadFile(file);
    }

}
