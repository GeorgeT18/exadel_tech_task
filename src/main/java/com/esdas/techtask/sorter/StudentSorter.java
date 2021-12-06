package com.esdas.techtask.sorter;

import com.esdas.techtask.model.StudentRanking;
import com.esdas.techtask.model.StudentSortResult;

import java.util.List;

public interface StudentSorter {
    public StudentSortResult sort(List<StudentRanking> studentRankings);
}
