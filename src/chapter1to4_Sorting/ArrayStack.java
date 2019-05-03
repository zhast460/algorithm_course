package chapter1to4_Sorting;

public class ArrayStack {

    String[] s;
    int N = 0;

    public ArrayStack(int capacity){
        s = new String[capacity];
    }

    boolean isEmpty(){
        return N == 0;
    }

    int size(){
        return N;
    }


    void push(String item){
        if (N == s.length) resize(2 * N);
        s[N++] = item;
    }

    String pop(){
        String temp = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return temp;
    }

    private void resize(int newCapacity){
        String[] copy = new String[newCapacity];
        for (int i = 0; i < N; i++){
            copy[i] = s[i];
        }
        s = copy;
    }

    public static void main(String[] args){
        ArrayStack as = new ArrayStack(8);
        as.push("a");
        System.out.println(as.s.length);
        as.push("b");
        as.push("c");
        System.out.println(as.s.length);
        System.out.println(as.pop());
        System.out.println(as.s.length);
        System.out.println(as.pop());
        System.out.println(as.s.length);
    }
}
