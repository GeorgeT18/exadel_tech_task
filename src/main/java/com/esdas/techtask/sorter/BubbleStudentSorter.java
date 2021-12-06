package com.esdas.techtask.sorter;

import com.esdas.techtask.model.StudentRanking;

import java.util.List;

public class BubbleStudentSorter extends CustomStudentSorter {
    @Override
    protected void sortImpl(List<StudentRanking> studentRankings) {
        int size = studentRankings.size();

        for (int i = 0; i < size - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < size - i - 1; j++) {
                if (studentRankings.get(j).getStudentScore() > studentRankings.get(j + 1).getStudentScore()) {
                    StudentRanking temp = studentRankings.get(j);
                    studentRankings.set(j, studentRankings.get(j + 1));
                    studentRankings.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
