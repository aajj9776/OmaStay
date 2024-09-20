package com.omakase.omastay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@Service
public class FileUploadService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final Storage storage;

    @Autowired
    public FileUploadService(Storage storage) {
        this.storage = storage;
    }

    public String uploadFile(MultipartFile file, String subFolder, String fileName) throws Exception {
        String fullPath = subFolder + "/" + fileName;
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, fullPath).build(),
                file.getBytes()
        );
        return blobInfo.getMediaLink();
    }
    
}
