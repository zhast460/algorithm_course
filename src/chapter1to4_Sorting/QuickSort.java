package chapter1to4_Sorting;

public class QuickSort {

    static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (i < hi && a[++i] < a[lo]);
            while (j > lo && a[--j] > a[lo]);
            if (j <= i) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    static void exch (int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int k = partition(a, lo, hi);
        sort(a, lo, k-1);
        sort(a, k+1, hi);
    }

    static void sort(int[] a){
        // shuffle
        sort(a, 0, a.length-1);
    }

    public static void main(String[] args) {
        int[] a = {2,4,7,3,0,2,-2,4,-2};
        sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
