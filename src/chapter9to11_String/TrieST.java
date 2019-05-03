package chapter9to11_String;

import java.util.LinkedList;
import java.util.Queue;

public class TrieST<Value> {

    private static class Node{
        private Object value;
        private Node[] next = new Node[R];
    }

    private static final int R = 128;
    private Node root = new Node();

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()){
            x.value = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Iterable<String> keys() {
        Queue<String> queue = new LinkedList<>();
        collect(root, "", queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> queue) {
        if (x == null) return;
        if (x.value != null) queue.add(prefix);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, queue);
        }
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Node node = get(root, prefix, 0);
        Queue<String> queue = new LinkedList<>();
        collect(node, prefix, queue);
        return queue;
    }

    public String longestPrefixOf(String query) {
        int length = search(root, query, 0, 0);
        return query.substring(0, length);
    }

    private int search(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.value != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return search(x.next[c], query, d+1, length);
    }

    public static void main(String[] args) {
        TrieST<Integer> trie = new TrieST<>();
        trie.put("hat", 13);
        trie.put("hatat", 15);
        trie.put("hah", 3);
        trie.put("tst", 4);
        System.out.println(trie.get("hatat"));
        System.out.println(trie.get("hata"));
        System.out.println(trie.get("hah"));
        System.out.println(trie.get("tst"));
        System.out.println(trie.contains("hat"));
        System.out.println();
        for (String s : trie.keys())
            System.out.print(s + " ");
        System.out.println();
        for (String s : trie.keysWithPrefix("ha"))
            System.out.print(s + " ");
        System.out.println();
        System.out.println(trie.longestPrefixOf("hatbtat"));
    }
}

