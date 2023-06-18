package ru.egrva.sorts;

import org.openjdk.jmh.annotations.Benchmark;

public interface AbstractSort {

//    @Benchmark
    int[] sort(int[] arr);

    default long startSort(int[] arr) {
        long startTime = System.currentTimeMillis();
        sort(arr);
        return System.currentTimeMillis() - startTime;
    }
}

