package org.example.task2;

import lombok.SneakyThrows;

public class ShellSort {

    @SneakyThrows
    public static int[] sort(int array[]) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int n = array.length;
        for (int interval = n / 2; interval > 0; interval /= 2) {
            for (int i = interval; i < n; i += 1) {
                int temp = array[i];
                int j;
                for (j = i; j >= interval && array[j - interval] > temp; j -= interval) {
                    array[j] = array[j - interval];
                }
                array[j] = temp;
            }
        }
        return array;
    }
}
