package vn.edu.funix.algo.sort;

public class Selection {
    public static float[] sort(float[] inputArr, boolean isStepPrinted) {
        float[] arr = inputArr.clone();

        int sortIdx = -1;
        while (++sortIdx < arr.length) {
            int smallestIdx = sortIdx;

            for (int i = sortIdx; i < arr.length; i++)
                if (arr[i] < arr[smallestIdx])
                    smallestIdx = i;

            if (sortIdx != smallestIdx)
                Util.swap(arr, sortIdx, smallestIdx);

            if (isStepPrinted) Util.printArr(arr);
        }

        return arr;
    }

    public static float[] sort(float[] inputArr) {
        return sort(inputArr, false);
    }
}
