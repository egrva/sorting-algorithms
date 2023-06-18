package ru.egrva.sorts.insertionsort;

import ru.egrva.sorts.AbstractSort;

public class InsertionSort implements AbstractSort {

    @Override
    public int[] sort(int[] arr) {
        int j;
        //сортировку начинаем со второго элемента, т.к. считается, что первый элемент уже отсортирован
        for (int i = 1; i < arr.length; i++) {
            //сохраняем ссылку на индекс предыдущего элемента
            int swap = arr[i];
            for (j = i; j > 0 && swap < arr[j - 1]; j--) {
                //элементы отсортированного сегмента перемещаем вперёд, если они больше элемента для вставки
                arr[j] = arr[j - 1];
            }
            arr[j] = swap;
        }
        return arr;
    }
}
