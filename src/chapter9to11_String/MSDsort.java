package chapter9to11_String;

public class MSDsort {

    static int R = 256;

    public static void sort(String[] a) {
        String[] aux = new String[a.length];
        sort(a, aux, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, String[] aux, int lo, int hi, int d) {
        if (hi <= lo) return;
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++)
            count[charAt(a[i], d)+2]++;

        for (int i = 0; i < R + 1; i++)
            count[i+1] += count[i];

        for (int i = lo; i <= hi; i++)
            aux[count[charAt(a[i], d)+1]++] = a[i];

        for (int i = lo; i < hi; i++)
            a[i] = aux[i - lo];

        for (int i = 0; i < R; i++)
            sort(a, aux, lo + count[i], lo + count[i+1] - 1, d + 1);
    }

    private static int charAt(String s, int d){
        if (d < s.length()) return s.charAt(d);
        return -1;
    }

    public static void main(String[] args) {
        String a = "Compilation completed successfully in 2 s 77 ms do you know what I am saying";
        String[] array = a.split(" ");
        sort(array);
        for (String s : array)
            System.out.println(s);
    }
}
