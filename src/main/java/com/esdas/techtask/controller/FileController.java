package com.esdas.techtask.controller;

import com.esdas.techtask.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("{fileName}")
    public ResponseEntity<Byte> getFile(@PathVariable String fileName) throws IOException {
        String filePath = fileService.getPath(fileName);

        InputStream inputStream = new FileInputStream(filePath);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(Files.size(Paths.get(filePath)));
        headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        return new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
    }
}
