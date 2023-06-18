package ru.egrva.sorts.countingsort;

import ru.egrva.sorts.AbstractSort;

public class CountingSort implements AbstractSort {
    @Override
    public int[] sort(int[] arr) {

        int numbersCount = arr.length;
        int maxNumber = Integer.MIN_VALUE;
        int minNumber = Integer.MAX_VALUE;

        for (int i = 0; i < numbersCount; i++) {
            int nextInt = arr[i];
            if (nextInt > maxNumber) {
                maxNumber = nextInt;
            }
            if (nextInt < minNumber) {
                minNumber = nextInt;
            }
        }

        int range = Math.abs(minNumber) + Math.abs(maxNumber) + 1;

        int[] sortingArr = new int[range];

        for (int i = 0; i < numbersCount; i++) {
            int currentNumber = arr[i];
            sortingArr[currentNumber] += 1;
        }

        int pointer = 0;
        for (int i = 0; i < range; i++) {
            int numberCount = sortingArr[i];
            while(numberCount > 0) {
                arr[pointer] = i;
                numberCount--;
                pointer++;
            }
        }
        return arr;
    }
}

