package com.esdas.techtask.sorter;

import com.esdas.techtask.exception.SorterNotFoundException;
import com.esdas.techtask.model.StudentRanking;
import com.esdas.techtask.model.StudentSortResult;

import java.util.List;

abstract public class CustomStudentSorter implements StudentSorter {
    public static StudentSorter getByString(String sorterName) throws SorterNotFoundException {
        switch (sorterName) {
            case "bubble":
                return new BubbleStudentSorter();
            case "heap":
                return new HeapStudentSorter();
            case "merge":
                return new MergeStudentSorter();
        }

        throw new SorterNotFoundException();
    }

    abstract protected void sortImpl(List<StudentRanking> studentRankings);

    public StudentSortResult sort(List<StudentRanking> studentRankings) {
        Long currentTimeInMilliseconds = System.currentTimeMillis();

        this.sortImpl(studentRankings);

        return new StudentSortResult(System.currentTimeMillis() - currentTimeInMilliseconds);
    }
}
