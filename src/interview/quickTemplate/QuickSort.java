package interview.quickTemplate;

import java.util.Arrays;

// an important template since quicksort has a few tricky places
public class QuickSort {

    // MORE PREFERRED
    public void sort(int[] a, int start, int end) {
        if (start >= end) return;
        int p = partition(a, start, end);
        sort(a, start, p - 1);
        sort(a, p + 1, end);
    }

    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    // MORE PREFERRED, partitionWithValue with range, take RIGHTMOST as pivot
    public int partition(int[] a, int start, int end) {
        int pivot = a[end];
        for (int i = start; i < end; i++) {
            if (a[i] < pivot) {
                exch(a, i, start++);
            }
        }
        exch(a, start, end);
        return start;
    }

    // sort2 is NOT recommended, a few "<=", hard to remember
    public void sort2(int[] a, int start, int end) {
        if (start >= end) return;
        int i = start, j = end, pivot = a[(i + j) / 2];
        while (i <= j) {
            while (i <= j && a[i] < pivot) i++;
            while (i <= j && a[j] > pivot) j--;
            if (i <= j) exch(a, i++, j--);
        }
        sort2(a, start, j);
        sort2(a, i, end);
    }

    public void sort2(int[] a) {
        sort2(a, 0, a.length - 1);
    }

    // return k-th largest, k starts from 1
    public int quickSelect(int[] a, int k) {
        k = a.length - k; // change to find k-th smallest, k starts from 0
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int partition = partition(a, lo, hi);
            if (partition == k) {
                break;
            } else if (partition > k) {
                hi = partition - 1;
            } else {
                lo = partition + 1;
            }
        }
        return a[k];
    }

    // partitionWithValue with pivot. apply to partitionWithValue-ONLY question (LintCode31), return the first position of element >= pivot
    public int partitionWithValue(int[] a, int pivot) {
        int start = 0, end = a.length - 1;
        while (start < end) {
            while (start < end && a[start] < pivot) start++;
            while (start < end && a[end] >= pivot) end--;
            if (start < end) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
        }
        if (a[start] < pivot) // note that have to do checking here
            return start + 1;
        else
            return start;
    }

    private void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort sol = new QuickSort();

        int[] a = {4,3,1,2,3,7}; // 1,2,3,3,4,7
        System.out.println("partitionWithValue a: " + sol.partitionWithValue(a, 6));

        int[] b = {9,5,1,4,6,5,7,5,0,8};
        int[] c = Arrays.copyOf(b, b.length);
        int[] d = Arrays.copyOf(b, b.length);

        sol.sort(b);
        System.out.print("sort b: ");
        for (int i : b) System.out.print(i + " ");
        System.out.println();

        sol.sort2(c);
        System.out.print("sort2 c: ");
        for (int i : c) System.out.print(i + " ");
        System.out.println();

        System.out.println("quick select 3rd largest: " + sol.quickSelect(d, 3));
    }
}
