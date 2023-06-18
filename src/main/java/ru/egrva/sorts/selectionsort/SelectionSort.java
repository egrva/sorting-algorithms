package ru.egrva.sorts.selectionsort;

import ru.egrva.sorts.AbstractSort;

public class SelectionSort implements AbstractSort {
    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //pos - индекс наименьшего элемента
            int pos = i;
            //pos - наименьший элемент
            int min = arr[i];
            //цикл выбора наименьшего элемента
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    pos = j;
                    min = arr[j];
                }
            }
            //меняем местами наименьший с arr[i]
            arr[pos] = arr[i];
            arr[i] = min;
        }
        return arr;
    }
}
