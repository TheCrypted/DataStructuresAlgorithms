package ProjectProgredior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static ProjectProgredior.IterativeSorts.*;
import static ProjectProgredior.DoCSorts.*;


public class ProjectProgredior {

    public static void main(String[] args) {
        int[] test = {125, 63, 54, 119, 20,43,67,87,95,235,22};
        quickSort(test);
        System.out.println(Arrays.toString(test));
    }
}
