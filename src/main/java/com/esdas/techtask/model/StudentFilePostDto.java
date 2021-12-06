package com.esdas.techtask.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class StudentFilePostDto {
    private String sortingAlgorithm;
    private MultipartFile file;
}
