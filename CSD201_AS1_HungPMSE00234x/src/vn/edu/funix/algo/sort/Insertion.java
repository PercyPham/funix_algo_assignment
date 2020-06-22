package vn.edu.funix.algo.sort;

public class Insertion {
    public static float[] sort(float[] inputArr, boolean isStepPrinted) {
        float[] arr = inputArr.clone();

        for (int i = 0; i < arr.length; i++) {
            float current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;

            if (isStepPrinted) Util.printArr(arr);
        }

        return arr;
    }

    public static float[] sort(float[] inputArr) {
        return sort(inputArr, false);
    }
}
