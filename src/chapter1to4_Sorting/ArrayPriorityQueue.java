package chapter1to4_Sorting;

public class ArrayPriorityQueue {

    static int[] a;
    static int N;

    ArrayPriorityQueue(int capacity) {
        a = new int[capacity];
    }

    static void insert(int i) {
        a[N++] = i;
    }

    int delMax() {
        int max = 0;
        for (int i = 0; i < N; i++){
            if (a[i] > a[max]) {
                max = i;
            }
        }
        exch(a, max, N-1);
        return a[--N];
    }

    void exch (int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        ArrayPriorityQueue pq = new ArrayPriorityQueue(10);
        pq.insert(3);
        pq.insert(9);
        pq.insert(7);
        pq.insert(4);
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
    }
}
