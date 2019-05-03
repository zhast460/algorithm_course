package chapter1to4_Sorting;

public class HeapSort {

    static void sort(int[] a){
        int N = a.length;
        for (int i = N / 2; i > 0; i--){
            sink(a, i, N);
        }
    }

    static void sink(int[] keys, int k, int N){
        while (k * 2 <= N){
            int j = k * 2;
            if (j < N && keys[j] > keys[j-1]) j++;
            if (keys[k-1] >= keys[j-1]) break;
            exch(keys, k, j);
            k = j;
        }
    }

    static void exch(int[] keys, int i, int j){
        int temp = keys[i-1];
        keys[i-1] = keys[j-1];
        keys[j-1] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{6,3,9,4,8};
        sort(a);
        for (int i : a)
            System.out.println(i);
    }
}
