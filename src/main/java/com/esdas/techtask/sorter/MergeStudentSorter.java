package com.esdas.techtask.sorter;

import com.esdas.techtask.model.StudentRanking;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MergeStudentSorter extends CustomStudentSorter {
    @Override
    protected void sortImpl(List<StudentRanking> studentRankings) {
        this.mergeSort(studentRankings, studentRankings.size());

    }

    public void mergeSort(List<StudentRanking> studentRankings, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        List<StudentRanking> l = new ArrayList<>();
        List<StudentRanking> r = new ArrayList<>();

        for (int i = 0; i < mid; i++) {
            l.add(i, studentRankings.get(i));
        }
        for (int i = mid; i < n; i++) {
            r.add(i - mid, studentRankings.get(i));
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(studentRankings, l, r, mid, n - mid);
    }

    public void merge(List<StudentRanking> studentRankings, List<StudentRanking> l, List<StudentRanking> r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l.get(i).getStudentScore() <= r.get(j).getStudentScore()) {
                studentRankings.set(k++, l.get(i++));
            }
            else {
                studentRankings.set(k++, r.get(j++));
            }
        }
        while (i < left) {
            studentRankings.set(k++, l.get(i++));
        }
        while (j < right) {
            studentRankings.set(k++, r.get(j++));
        }
    }
}
