package ProjectProgredior;

import java.util.Random;

public class DoCSorts {
    private static void swapElem(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
    public static void mergeSort(int[] inputArr) {
        int length = inputArr.length;
        if (length <= 1) return;
        else if (length == 2 && inputArr[0] > inputArr[1]) {
            swapElem(inputArr, 0, 1);
            return;
        } else {
            int mid = length / 2;
            int midR = length%2 == 0? mid : mid + 1;
            int[] left = new int[mid];
            int[] right = new int[midR];
            for(int i = 0; i < mid; i++){
                left[i] = inputArr[i];
                right[i] = inputArr[mid + i];
            }
            if(length%2 != 0) right[mid] = inputArr[length-1];
            mergeSort(left);
            mergeSort(right);
            int i = 0, j = 0;
            while(i < mid && j < midR){
                if(left[i]<=right[j]){
                    inputArr[i+j] = left[i];
                    i++;
                } else {
                    inputArr[i+j] = right[j];
                    j++;
                }
            }
            while(i<mid){
                inputArr[i+j] = left[i];
                i++;
            }
            while(j<midR){
                inputArr[i+j] = right[j];
                j++;
            }
        }
    }
    public static void quickSort(int[] inputArr){
        quickSort(inputArr, 0, inputArr.length-1);
    }
    private static void quickSort(int[] inputArr, int startInd, int endInd){
        if(startInd >= endInd) return;
        else if (endInd-startInd == 1 && inputArr[startInd] > inputArr[endInd]){
            swapElem(inputArr, startInd, endInd);
            return;
        } else if (endInd - startInd == 1 && inputArr[startInd] <= inputArr[endInd]) return;
        else {
            Random rand = new Random();
            int pivot = rand.nextInt(startInd, endInd);
            int pivotVal = inputArr[pivot];
            swapElem(inputArr, startInd, pivot);
            int i = startInd + 1, j = endInd;
            while (i <= j) {
                while (i <= j && inputArr[i] <= pivotVal) {
                    i++;
                }
                while (i <= j && inputArr[j] >= pivotVal) {
                    j--;
                }
                if (i <= j) {
                    swapElem(inputArr, j, i);
                    i++;
                    j--;
                }

            }
            swapElem(inputArr, j, startInd);
            quickSort(inputArr, startInd, j - 1);
            quickSort(inputArr, j + 1, endInd);
        }
    }

    public static void countingSort(int[] inputArr, int start, int end) {
        int[] countArr = new int[end - start + 1];
        for (int num : inputArr) {
            countArr[num - start]++;
        }
        int k = 0;
        for (int i = 0; i < countArr.length; i++) {
            int count = countArr[i];
            for (int j = 0; j < count; j++) {
                inputArr[k++] = i + start;
            }
        }
    }
    public static void lsdRadixSort(int[] inputArr){
        int max = inputArr[0];
        for (int j : inputArr) {
            max = Math.max(max, j);
        }
        int len = Integer.toString(max).length();
        for(int i = 1; i <= len; i++){
            countingSortSpec(inputArr, (int)Math.pow(10 , i-1));
        }
    }
    private static void countingSortSpec(int[] inputArr, int exp){
        int  n = inputArr.length;
        int[] output = new int[n];
        int[] sortArr = new int[10];
        int conc = 0;
        for(int number : inputArr){
            sortArr[(number/exp)%10]++;
        }
        for (int i = 1; i < 10; i++) {
            sortArr[i] += sortArr[i - 1];
        }
        for(int i = n-1; i >= 0; i--) {
            output[sortArr[(inputArr[i]/exp)%10] - 1] = inputArr[i];
            sortArr[(inputArr[i]/exp)%10] --;
        }
        for(int i = 0; i < inputArr.length; i++) {
            inputArr[i] = output[i];
        }
    }
//    public static void countingSortA(int[] inputArr, int start, int end) {
//        int[] sortArr = new int[end - start + 1];
//        int netCount = 0;
//        for (int number : inputArr) {
//            sortArr[number - start] ++;
//        }
//        for (int i = 0; i < sortArr.length; i++) {
//            netCount += sortArr[i];
//            sortArr[i] = netCount;
//        }
//        for(int i = 0; i < sortArr.length; i++) {
//            int index = i == 0 ? 0 : sortArr[i-1];
//            inputArr[index] = i + start;
//            int j = i == sortArr.length - 1 ? inputArr.length - index - 1 : sortArr[i] - index;
//            while (j > 0) {
//                inputArr[index + j] = i + start;
//                j--;
//            }
//        }
//    }
}
