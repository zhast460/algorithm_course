package chapter9to11_String;

// fixed length string sort
public class LSDsort {

    public static void sort(String[] a, int W) {
        int R = 256;
        int N = a.length;
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d)+1]++;
            for (int i = 0; i < R; i++)
                count[i+1] += count[i];
            for (int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        String[] a = {"fsd", "eee", "aea", "eae", "b92"};
        sort(a, 3);
        for (String i : a)
            System.out.println(i);
    }
}
