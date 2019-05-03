package chapter1to4_Sorting;

public class MaxHeapPriorityQueue {

    int[] keys;
    int N;

    public MaxHeapPriorityQueue(int capacity){
        keys = new int[capacity+1];
    }

    boolean isEmpty(){
        return N == 0;
    }

    void swim(int k){
        while (k > 1 && keys[k] > keys[k/2]){
            exch(keys, k, k/2);
            k = k/2;
        }
    }

    void sink(int k){
        while (k * 2 <= N){
            int j = k * 2;
            if (j < N && keys[j+1] > keys[j]) j++;
            if (keys[k] >= keys[j]) break;
            exch(keys, k, j);
            k = j;
        }
    }

    void insert(int x){
        keys[++N] = x;
        swim(N);
    }

    int delMax(){
        int res = keys[1];
        exch(keys, 1, N);
        keys[N--] = 0;
        sink(1);
        return res;
    }

    void exch(int[] keys, int i, int j){
        int temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }

    public static void main(String[] args) {
        MaxHeapPriorityQueue pq = new MaxHeapPriorityQueue(10);
        pq.insert(4);
        pq.insert(9);
        pq.insert(6);
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        pq.insert(8);
        pq.insert(2);
        System.out.println(pq.delMax());
    }
}
