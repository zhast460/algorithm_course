package interview.leetcode;

import java.util.HashMap;
import java.util.Map;

class LC146_LRU_Cache {

    private class Node{
        int k, v;
        Node prev, next;
        Node(int k, int v){
            this.k = k;
            this.v = v;
        }
        Node(){
            this(0,0);
        }
    }

    private int capacity, count;
    private Map<Integer, Node> map;
    private Node head, tail;

    public LC146_LRU_Cache(int capacity) {
        this.capacity = capacity;
        count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null) return -1;
        update(n);
        return n.v;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if (n != null){
            n.v = value;
            update(n);
        }else{
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            add(newNode);
            count++;
            if (count > capacity){
                map.remove(tail.prev.k);
                remove(tail.prev);
                count--;
            }
        }
    }

    private void add(Node n){
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    private void remove(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void update(Node n){
        remove(n);
        add(n);
    }

    public static void main(String[] args) {
        LC146_LRU_Cache lruc = new LC146_LRU_Cache(2);
        lruc.put(1,1);
        lruc.put(2,2);
        System.out.println(lruc.get(1));
        lruc.put(3,3);
        System.out.println(lruc.get(2));
        lruc.put(4,4);
        System.out.println(lruc.get(1));
        System.out.println(lruc.get(3));
        System.out.println(lruc.get(4));
        Math.pow(2,4);
    }
}

/**
 * Your interview.leetcode.LC146_LRU_Cache object will be instantiated and called as such:
 * interview.leetcode.LC146_LRU_Cache obj = new interview.leetcode.LC146_LRU_Cache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */