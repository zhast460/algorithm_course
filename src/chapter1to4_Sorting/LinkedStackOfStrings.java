package chapter1to4_Sorting;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Objects;

public class LinkedStackOfStrings<T> implements Iterable<T>{

    private class Node<T>{
        T s;
        Node<T> next;
    }

    Node<T> head = null;

    int size = 0;

    boolean isEmpty(){return head == null;}

    int size(){return size;}

    void push(T s){

        Node<T> n = new Node();
        n.s = s;
        n.next = head;
        head = n;
        size++;
    }

    T pop(){
        T t = head.s;
        head = head.next;
        size--;
        return t;
    }

    public Iterator<T> iterator(){
        return new StackIterator<T>(head);
    }

    private class StackIterator<T> implements Iterator<T> {
        private Node<T> current;

        public StackIterator(Node head){
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) throw new RuntimeException("The stack is empty!");
            T res = current.s;
            current = current.next;
            return res;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedStackOfStrings<?> that = (LinkedStackOfStrings<?>) o;
        return size == that.size &&
                Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }

    public static void main(String[] a){
        var ls = new LinkedStackOfStrings<BigDecimal>();
        ls.push(new BigDecimal("1111"));
        ls.push(new BigDecimal("2.22"));
        ls.push(new BigDecimal("333"));
        for (BigDecimal b : ls)
            System.out.println(b.toString());

        System.out.println(" ");
        System.out.println(ls.isEmpty());
        System.out.println(ls.pop());
        System.out.println(ls.pop());
        System.out.println(ls.size());
        System.out.println(ls.pop());
        System.out.println(ls.isEmpty());
    }
}
