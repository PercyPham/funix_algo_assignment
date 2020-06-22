package vn.edu.funix.algo.sort;

public class Util {
    static void swap(float[] arr, int idx1, int idx2) {
        float temp = arr[idx2];
        arr[idx2] = arr[idx1];
        arr[idx1] = temp;
    }

    static void printArr(float[] arr) {
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            s += arr[i] + " ";
        }
        System.out.println(s.trim());
    }
}
