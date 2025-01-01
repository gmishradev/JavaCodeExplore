package com.code.sort;

public class MergeSort {

    // Main function to sort an array using merge sort
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // Base case: arrays with fewer than 2 elements are already sorted
        }
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // Copy data to temp arrays
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Recursively sort the sub-arrays
        mergeSort(left);
        mergeSort(right);

        // Merge sorted sub-arrays
        merge(array, left, right);
    }

    // Function to merge two sorted arrays
    private static void merge(int[] array, int[] left, int[] right) {
        int leftIndex = 0, rightIndex = 0, arrayIndex = 0;

        // Merge the two arrays
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                array[arrayIndex++] = left[leftIndex++];
            } else {
                array[arrayIndex++] = right[rightIndex++];
            }
        }

        // Copy remaining elements of left array, if any
        while (leftIndex < left.length) {
            array[arrayIndex++] = left[leftIndex++];
        }

        // Copy remaining elements of right array, if any
        while (rightIndex < right.length) {
            array[arrayIndex++] = right[rightIndex++];
        }
    }

    // Main method to test merge sort
    public static void main(String[] args) {
        int[] array = { 38, 27, 43, 3, 9, 82, 10 };
        System.out.println("Original array: ");
        printArray(array);

        mergeSort(array);

        System.out.println("Sorted array: ");
        printArray(array);
    }

    // Utility method to print an array
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
