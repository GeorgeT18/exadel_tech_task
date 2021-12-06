package com.esdas.techtask.service;


import com.esdas.techtask.exception.SorterNotFoundException;
import com.esdas.techtask.model.StudentRanking;
import com.esdas.techtask.model.StudentRankingsWithStudentSortResult;
import com.esdas.techtask.model.StudentSortResult;
import com.esdas.techtask.sorter.CustomStudentSorter;
import com.esdas.techtask.sorter.StudentSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class StudentService {
    @Autowired
    private FileService fileService;

    public StudentSortResult sortStudentRankings(List<StudentRanking> studentRankings, String sortingAlgorithmName) throws SorterNotFoundException {
        StudentSorter studentSorter = CustomStudentSorter.getByString(sortingAlgorithmName);

        StudentSortResult studentSortResult = studentSorter.sort(studentRankings);

        return new StudentSortResult(studentSortResult.getTimeTakenInMilliSeconds());
    }

    public String writeStudentRankingsToTxtFile(List<StudentRanking> studentRankings) throws FileNotFoundException {
        String fileName = fileService.generateUniqueFileName("txt");

        PrintWriter out = new PrintWriter(fileService.getPath(fileName));

        for (StudentRanking studentRanking : studentRankings) {
            out.println(studentRanking.getStudentName() + "," + studentRanking.getStudentScore());
        }

        out.close();

        return fileName;
    }

    public List<StudentRanking> processFileToStudentRankings(MultipartFile multipartFile) throws IOException {
        String fileName = fileService.uploadFile(multipartFile);

        List<String> lines = Files.readAllLines(Paths.get(fileService.getPath(fileName)));
        List<StudentRanking> studentRankings = new ArrayList<>();

        for (String line : lines) {
            List<String> items = Arrays.asList(line.split("\\s*,\\s*"));

            String name = items.get(0);
            Float price = Float.parseFloat(items.get(1));

            studentRankings.add(new StudentRanking(name, price));
        }

        return studentRankings;
    }

    public StudentRankingsWithStudentSortResult processSortSaveFileAndGetStudentRankings(MultipartFile multipartFile, String sortingAlgorithmName) throws IOException, SorterNotFoundException {
        // turn file into arrayList
        List<StudentRanking> studentRankings = this.processFileToStudentRankings(multipartFile);

        // sort the returned arraylist
        StudentSortResult studentSortResult = this.sortStudentRankings(studentRankings, sortingAlgorithmName);

        // create .txt file of sorted students
        String fileName = this.writeStudentRankingsToTxtFile(studentRankings);

        return new StudentRankingsWithStudentSortResult(studentSortResult, studentRankings, fileName);
    }
}
