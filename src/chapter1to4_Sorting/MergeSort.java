package chapter1to4_Sorting;

public class MergeSort {

    static void merge(int[] allSortedWhenMergeFinished, int[] partial, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) allSortedWhenMergeFinished[k] = partial[j++];
            else if (j > hi) allSortedWhenMergeFinished[k] = partial[i++];
            else if (partial[i] < partial[j]) allSortedWhenMergeFinished[k] = partial[i++];
            else allSortedWhenMergeFinished[k] = partial[j++];
        }
        assert  isSorted(allSortedWhenMergeFinished, lo, hi);
    }

    static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (a[i] > a[i+1]) return false;
        }
        return true;
    }

    static void sort(int[] partial, int[] allSortedWhenFinished, int lo, int hi) {
        if (hi <= lo) return;
        int mid = (lo + hi) / 2;
        sort(allSortedWhenFinished, partial, lo, mid);
        sort(allSortedWhenFinished, partial ,mid + 1, hi);
        if (partial[mid] <= partial[mid+1]) return;
        merge(allSortedWhenFinished, partial, lo, mid, hi);
    }

    public static void main(String[] args) {
        int[] a = {2,4,7,3,0,2,-2,4,-2};
        int[] aux = {2,4,7,3,0,2,-2,4,-2};
        sort(a, aux, 0, a.length-1);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
