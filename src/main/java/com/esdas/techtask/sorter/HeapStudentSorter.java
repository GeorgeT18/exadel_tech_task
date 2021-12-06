package com.esdas.techtask.sorter;

import com.esdas.techtask.model.StudentRanking;

import java.util.ArrayList;
import java.util.List;

public class HeapStudentSorter extends CustomStudentSorter {
    @Override
    protected void sortImpl(List<StudentRanking> studentRankings)
    {
        int size = studentRankings.size();

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(studentRankings, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            StudentRanking temp = studentRankings.get(0);
            studentRankings.set(0, studentRankings.get(i));
            studentRankings.set(i, temp);

            heapify(studentRankings, i, 0);
        }
    }

    void heapify(List<StudentRanking> studentRankings, int n, int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && studentRankings.get(l).getStudentScore() > studentRankings.get(largest).getStudentScore()) {
            largest = l;
        }

        if (r < n && studentRankings.get(r).getStudentScore() > studentRankings.get(largest).getStudentScore()) {
            largest = r;
        }

        if (largest != i) {
            StudentRanking temp = studentRankings.get(i);
            studentRankings.set(i, studentRankings.get(largest));
            studentRankings.set(largest, temp);

            heapify(studentRankings, n, largest);
        }
    }
}
