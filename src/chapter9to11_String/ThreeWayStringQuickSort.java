package chapter9to11_String;

public class ThreeWayStringQuickSort {
    public static void sort(String[] a){
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;;
        int v = charAt(a[lo], d);
        int lt = lo, gt  = hi, i = lo;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, i++, lt++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1, d);
        if (v > 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);

    }

    private static int charAt(String s, int d){
        if (d < s.length()) return s.charAt(d);
        return -1;
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String a = "Compilation completed successfully in 2 s 77 ms do you know what I am saying";
        String[] array = a.split(" ");
        sort(array);
        for (String s : array)
            System.out.println(s);
    }
}
