package ru.serdyuk.insertionsort;

import java.util.Arrays;

/**
 * @author Serdyuk S.B.
 * 1) берем текущий элемент currentElement
 * 2) разделяем массив (по текущему элементу) на два массива, назовем их:
 * sorted - unsorted соответственно. вначале отсортированный массив пуст.
 * И в цикле проверять текущий элемент с отсортированными и ставить его перед меньшим.
 * O(N^2)
 * простанственная сложность О(N) + O(1)
 * алгоритм устойчив
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {9,8,7,6,5,4,3,2,1,0};
        //insertionSort(array);
        insertSotFor(array);
        System.out.println(Arrays.toString((array)));
    }
    private static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int currentElement = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > currentElement) {
                array[j] = array[j - 1];
                j--;
            }

            array[j] = currentElement;
        }

    }

    private static void insertSotFor(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (value < array[j]) {
                    array[j + 1] = array[j];
                }
            }
            array[j + 1] = value;
        }
    }

    private static int[] insSort(int[] src) {
        for (int i = 1; i < src.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (src[j] < src[j - 1]) {
                    int temp = src[j];
                    src[j] = src[j - 1];
                    src[j - 1] = temp;
                }else break;
            }
        }
        return src;
    }
}
