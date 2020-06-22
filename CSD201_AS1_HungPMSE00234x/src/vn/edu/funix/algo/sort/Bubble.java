package vn.edu.funix.algo.sort;

public class Bubble {
    public static float[] sort(float[] inputArr, boolean isStepPrinted) {
        float[] arr = inputArr.clone();

        for (int lastNum = arr.length; lastNum > 0; lastNum--) {
            for (int i = 0; i < lastNum - 1; i++)
                if (arr[i] > arr[i + 1])
                    Util.swap(arr, i, i + 1);

            if (isStepPrinted) Util.printArr(arr);
        }

        return arr;
    }

    public static float[] sort(float[] inputArr) {
        return sort(inputArr, false);
    }
}
