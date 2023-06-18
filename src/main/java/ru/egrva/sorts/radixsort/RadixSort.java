package ru.egrva.sorts.radixsort;

import ru.egrva.sorts.AbstractSort;

public class RadixSort implements AbstractSort {

    @Override
    public int[] sort(int[] arr) {
        //определяем длину максимального числа в массиве
        int maxLength = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        maxLength = String.valueOf(max).length();

        int range = 10;
        int[] counter = new int[10];
        int[][] array = new int[range][arr.length];
        int pointer = 0;
        //распределяем числа по массивам, начиная с правого разряда
        for (int i = 0; i < maxLength; i++) {
            double dec = Math.pow(range, i);
            //заполняем массив
            for (int j = 0; j < arr.length; j++) {
                //находим нужный нам разряд
                int d = ((int) (arr[j] / dec)) % 10;
                array[d][counter[d]] = arr[j];
                counter[d] += 1;
            }
            for (int k = 0; k < range; k++) {
                int point = counter[k];
                for (int f = 0; f < point; f++) {
                    final int sd = array[k][f];
                    arr[pointer] = sd;
                    array[k][f] = 0;
                    pointer += 1;
                }
                counter[k] = 0;
            }
            pointer = 0;
        }
        return arr;
    }
}

