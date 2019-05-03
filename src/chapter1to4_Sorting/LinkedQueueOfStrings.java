package chapter1to4_Sorting;

import java.util.Iterator;

public class LinkedQueueOfStrings implements Iterable<String>{

    private class Node{
        String s;
        Node next;

        public Node(String s){
            this.s = s;
        }
    }

    private class QueueIterator implements Iterator<String>{

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public String next() {
            String item = current.s;
            current = current.next;
            return item;
        }
    }


    Node head = null;
    Node tail = null;
    int size = 0;

    @Override
    public Iterator<String> iterator() {
        return new QueueIterator();
    }

    boolean isEmpty(){return head == null;}

    void enqueue(String s){
        Node oldLast = tail;
        Node n = new Node(s);
        tail = n;
        if (isEmpty()) head = tail;
        else oldLast.next = tail;
        size++;
    }

    String dequeue(){
        Node n = head;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return n.s;
    }

    public static void main (String[] args){
        LinkedQueueOfStrings lq = new LinkedQueueOfStrings();
        lq.enqueue("YER");
        lq.enqueue("2b");
        lq.enqueue("cC");
        for (String s : lq){
            System.out.println(s);
        }
        System.out.println();
        System.out.println(lq.size);
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        System.out.println(lq.isEmpty());
        System.out.println(lq.dequeue());
        System.out.println(lq.isEmpty());
        System.out.println(lq.tail);
    }
}
