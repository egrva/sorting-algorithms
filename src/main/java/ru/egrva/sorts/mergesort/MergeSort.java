package ru.egrva.sorts.mergesort;

import ru.egrva.sorts.AbstractSort;

import java.util.Arrays;

public class MergeSort implements AbstractSort {
    @Override
    public int[] sort(int[] arr) {
        //скопировали исходный массив
        int[] buffer1 = Arrays.copyOf(arr, arr.length);
        //создали новый пустой массив с размерностью исходного
        int[] buffer2 = new int[arr.length];
        //запустили алгоритм сортировки слиянием
        return mergeSortInner(buffer1, buffer2, 0, arr.length);
    }

    private int[] mergeSortInner(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {

        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        //рекурсивный запуск алгоритма
        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);


        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;

        int[] result = ((sorted1 == buffer1) ? buffer2 : buffer1);
        //пока элементы есть и в первом и во втором массиве
        while (index1 < middle && index2 < endIndex) {
            //если элемент первого массива меньше, чем элемент второго
            if (sorted1[index1] < sorted2[index2]) {
                // в результирующий массив кладем элемент первого массива
                result[destIndex] = sorted1[index1];
                destIndex++;
                index1++;
                //если элемент второго массива меньше, чем элемент первого
            } else {
                // в результирующий массив кладем элемент второго массива
                result[destIndex] = sorted2[index2];
                destIndex++;
                index2++;
            }
        }
        //пока в первом массиве еще остались элементы, кладем их в результирующий массив
        while (index1 < middle) {
            result[destIndex] = sorted1[index1];
            destIndex++;
            index1++;
        }
        //пока во втором массиве еще остались элементы, кладем их в результирующий массив
        while (index2 < endIndex) {
            result[destIndex] = sorted2[index2];
            destIndex++;
            index2++;
        }
        return result;
    }
}
