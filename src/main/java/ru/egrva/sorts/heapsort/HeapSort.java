package ru.egrva.sorts.heapsort;

import ru.egrva.sorts.AbstractSort;

public class HeapSort implements AbstractSort {

    @Override
    public int[] sort(int[] arr) {
        int n = arr.length;

        //шаг 1 - создаем max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        //шаг 2 - меняем корень и последний элемент. Уменьшаем размер кучи на 1
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            //преобразовываем кучу в max-heap с новым корнем
            heapify(arr, i, 0);
        }
        return arr;
    }

    void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}
