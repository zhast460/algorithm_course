package chapter5to6_Tree_SymbolTable;

import java.util.LinkedList;
import java.util.Queue;

public class LLRBBST {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node{
        Integer key;
        String value;
        Node left, right;
        int count;
        boolean color;

        public Node(Integer key, String value, int count){
            this.key = key;
            this.value = value;
            this.count = count;
        }

        public Node(Integer key, String value, int count, boolean color){
            this.key = key;
            this.value = value;
            this.count = count;
            this.color = color;
        }
    }

    public boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    public String get(Integer key){
        Node node = root;
        while (node != null){
            if (key > node.key) node = node.right;
            else if (key < node.key) node = node.left;
            else return node.value;
        }
        return null;
    }

    public void put(Integer key, String value){
        root = put(root, key, value);
    }

    private Node put(Node node, Integer key, String value){
        if (node == null) return new Node(key, value, 1, RED);
        //Integer cmp = key.compareTo(node.key);
        if (key < node.key)
            node.left = put(node.left, key, value);
        else if (key > node.key)
            node.right = put(node.right, key, value);
        else
            node.value = value;
        node.count = 1 + size(node.left) + size(node.right);

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = roateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    public Integer min(){
        Node minNode = min(root);
        if (minNode != null)
            return minNode.key;
        else
            return Integer.MIN_VALUE;
    }

    private Node min(Node node){
        if (node == null) return null;
        while (node.left != null)
            node = node.left;
        return node;
    }

    public Integer floor(Integer key){
        return floor(root, key);
    }

    private Integer floor(Node node, Integer key){
        if (node == null) return null;
        if (key < node.key){
            return floor(node.left, key);
        }else if (key > node.key){
            Integer integer = floor(node.right, key);
            if (integer != null)
                return integer;
            else
                return node.key;
        }else
            return node.key;
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        if (node == null)
            return 0;
        return node.count;
    }

    public int rank(Integer key) {
        return rank(root, key);
    }

    private int rank(Node node, Integer key){
        if (node == null)
            return 0;
        if (key < node.key)
            return rank(node.left, key);
        else if (key > node.key)
            return size(node.left) + 1 + rank(node.right, key);
        else
            return size(node.left);
    }

    public Iterable<Integer> keys(){
        Queue<Integer> queue = new LinkedList();
        inOrder(root, queue);
        return queue;
    }

    private void inOrder(Node node, Queue queue){
        if (node == null) return;
        inOrder(node.left, queue);
        queue.add(node.key);
        inOrder(node.right, queue);
    }

    public void deleteMin(){
        deleteMin(root);
    }

    private Node deleteMin(Node node){
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(Integer key){
        delete(root, key);
    }

    private Node delete(Node node, Integer key) {
        if (node == null) return null;
        if (key < node.key)
            node.left = delete(node.left, key);
        else if (key > node.key)
            node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node backup = node;
            node = min(node.right);
            node.right = deleteMin(backup.right);
            node.left = backup.left;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node rotateLeft(Node h) {
        assert (isRed(h.right));
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node roateRight(Node h) {
        assert (isRed(h.left));
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        assert (!isRed(h));
        assert (isRed(h.left));
        assert (isRed(h.right));
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    public static void main(String[] args){
        LLRBBST b = new LLRBBST();
        b.put(5, "five");
        b.put(2, "two");
        b.put(8, "eight");
        b.put(4, "four");
        b.put(7, "seven");
        b.put(11, "seven");
        System.out.println(b.get(4));
        System.out.println(b.min());
        System.out.println(b.floor(3));
        //System.out.println(b.size());
        System.out.println(b.rank(6));
        //b.delete(7);
        System.out.println(b.get(2));
        for (Integer key : b.keys())
            System.out.print(key + " ");
    }
}
