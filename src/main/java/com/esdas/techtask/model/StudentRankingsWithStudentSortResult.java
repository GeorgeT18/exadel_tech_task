package com.esdas.techtask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentRankingsWithStudentSortResult {
    StudentSortResult studentSortResult;
    List<StudentRanking> studentRankings;
    String resultFileName;
}
