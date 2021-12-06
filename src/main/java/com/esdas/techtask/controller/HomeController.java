package com.esdas.techtask.controller;

import com.esdas.techtask.exception.SorterNotFoundException;
import com.esdas.techtask.model.StudentFilePostDto;
import com.esdas.techtask.model.StudentRankingsWithStudentSortResult;
import com.esdas.techtask.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String submit(@ModelAttribute StudentFilePostDto studentFilePostDto, Model model) throws SorterNotFoundException, IOException {
        StudentRankingsWithStudentSortResult studentRankingsWithStudentSortResult = studentService.processSortSaveFileAndGetStudentRankings(studentFilePostDto.getFile(), studentFilePostDto.getSortingAlgorithm());

        model.addAttribute("studentRankings", studentRankingsWithStudentSortResult.getStudentRankings());
        model.addAttribute("sortTimeTakenInMilliSeconds", studentRankingsWithStudentSortResult.getStudentSortResult().getTimeTakenInMilliSeconds());
        model.addAttribute("sortedFileName", studentRankingsWithStudentSortResult.getResultFileName());

        return "result";
    }
}
