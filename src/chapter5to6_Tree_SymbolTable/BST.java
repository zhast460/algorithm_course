package chapter5to6_Tree_SymbolTable;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

    private class Node{
        Integer key;
        String value;
        Node left, right;
        int count;

        public Node(Integer key, String value, int count){
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }

    private Node root;

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
        if (node == null) return new Node(key, value, 1);
        //Integer cmp = key.compareTo(node.key);
        if (key < node.key)
            node.left = put(node.left, key, value);
        else if (key > node.key)
            node.right = put(node.right, key, value);
        else
            node.value = value;
        node.count = 1 + size(node.left) + size(node.right);
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

    public static void main(String[] args){
        BST b = new BST();
        b.put(5, "five");
        b.put(2, "three");
        b.put(8, "eight");
        b.put(4, "four");
        b.put(7, "seven");
        b.put(11, "seven");
        System.out.println(b.get(4));
        System.out.println(b.min());
        System.out.println(b.floor(3));
        System.out.println(b.size());
        System.out.println(b.rank(6));
        b.delete(7);
        for (Integer key : b.keys())
            System.out.print(key + " ");
    }
}
