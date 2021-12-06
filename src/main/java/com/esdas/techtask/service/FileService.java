package com.esdas.techtask.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    @Value("${local.uploadsDirectory}")
    private String uploadsDirectory;

    private String getFilePath() {
        return this.uploadsDirectory;
    }

    public String generateUniqueFileName(String ext) {
        return this.generateUniqueFileName() + "." + ext;
    }

    public String generateUniqueFileName() {
        return UUID.randomUUID().toString();
    }

    public String getNewFilePath(String ext) {
        return this.getNewFilePath() + "." + ext;
    }

    public String getNewFilePath() {
        return this.getFilePath() + this.generateUniqueFileName();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = this.generateUniqueFileName();

        file.transferTo(new File(this.getFilePath() + fileName));

        return fileName;
    }

    public String getPath(String fileName) {
        return this.getFilePath() + fileName;
    }
}
