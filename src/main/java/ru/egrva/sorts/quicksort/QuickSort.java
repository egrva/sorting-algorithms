package ru.egrva.sorts.quicksort;

import ru.egrva.sorts.AbstractSort;

public class QuickSort implements AbstractSort {

    @Override
    public int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSort(int[] arr, int low, int high) {
        //завершить, если массив пуст или уже нечего делить
        if (arr.length == 0 || low >= high) return;

        //выбираем опорный элемент
        int middle = low + (high - low) / 2;
        int border = arr[middle];

        //разделяем на подмассивы и меняем местами
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < border) {
                i++;
            }
            while (arr[j] > border) {
                j--;
            }
            if (i <= j) {
                int swap = arr[i];
                arr[i] = arr[j];
                arr[j] = swap;
                i++;
                j--;
            }
        }

        //рекурсия для сортировки левой и правой части
        if (low < j) quickSort(arr, low, j);
        if (high > i) quickSort(arr, i, high);
    }
}
