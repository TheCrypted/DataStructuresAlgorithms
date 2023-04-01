package ProjectProgredior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IterativeSorts {
    private static void swapElem(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
    private static int findMax(int[] inputArr){
        int max = 0;
        for (int j = 0; j <inputArr.length; j++) {
            if (inputArr[j] > inputArr[max]){
                max = j;
            }
        }
        return max;
    }
    public static void bubbleSortCSVariation(int[] inputArr){
        int i = 0;
        int startIndex = 0;
        int endIndex = inputArr.length - 1;
        int moves = 0;
        while(i+1<endIndex){
            if(inputArr[i] > inputArr[i+1]){
                swapElem(inputArr, i, i + 1);
                moves++;
            }
            i++;
        }
        startIndex++;
        while(i-1>=startIndex){
            if(inputArr[i-1] > inputArr[i]){
                swapElem(inputArr, i, i - 1);
                moves++;
            }
            i--;
        }
        endIndex--;
        if(moves == 0){
            return;
        }else {
            bubbleSortCSVariation(inputArr);
        }
    }

    public static void insertSort(int[] inputArr){
        int leftMark = 1;
        while (leftMark  < inputArr.length){
            int temp = leftMark;
            while(temp > 0 && inputArr[temp] < inputArr[temp-1]){
                swapElem(inputArr, temp, temp-1);
                temp--;
            }
            leftMark++;
        }
    }
    public static void selectionSort(int[] inputArr){
        int index = inputArr.length;
        while(index > 0){
            int max = 0;
            for (int j = 0; j < index; j++) {
                if (inputArr[j] > inputArr[max]){
                    max = j;
                }
            }
            if(max != index-1) {
                swapElem(inputArr, max, index - 1);
            }
            index--;
        }
    }

}
