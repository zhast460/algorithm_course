package chapter1to4_Sorting;

public class    ThreeWayQuickSort {

    static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int v = a[lo], lt = lo, i = lo, gt = hi;
        while (i <= gt) {
            int k = comp(a[i], v);
            if (k < 0) exch(a, i++, lt++);
            else if (k > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    static void exch (int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static int comp(int a, int b) {
        if (a > b) return 1;
        else if (a < b) return -1;
        else return 0;
    }

    public static void main(String[] args) {
        int[] a = {2,4,4,-2,4,2,-2,4,-2};
        sort(a,0, a.length-1);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
