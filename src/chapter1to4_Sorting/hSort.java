package chapter1to4_Sorting;

public class hSort {

    public static void main(String[] args){
        int[] a = {2,4,7,3,0,2,-2,4,-2,-7,4,-8,19,-14,8,6,9};
        hsort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

    static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j-1] > a[j]) exch(a,j-1,j);
                else break;
            }
        }
    }

    static void hsort(int[] a) {
        int h = 1;
        while (h < a.length/3) h = h*3 + 1;

        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && a[j-h] > a[j]; j -= h) exch(a, j-h, j);
            }
            h /= 3;
        }
    }

    static void exch(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
