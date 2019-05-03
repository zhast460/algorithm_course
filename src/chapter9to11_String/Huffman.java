package chapter9to11_String;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.PriorityQueue;

public class Huffman {

    // radix of extended ASCII
    private static final int R = 256;

    private static class Node{
        private char ch;
        private int freq;
        private Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void expand() {
        Node root = readTrie();
        int num = BinaryStdIn.readInt();

        for (int i = 0; i < num; i++) {
            Node curr = root;
            while (!curr.isLeaf()){
                if(BinaryStdIn.readBoolean())
                    curr = curr.right;
                else
                    curr = curr.left;
            }
            BinaryStdOut.write(curr.ch, 8);
        }
        BinaryStdOut.close();
    }

    public static void compress() {
        String input = BinaryStdIn.readString();
        char[] ca = input.toCharArray();
        int[] freq = new int[R];

        for (int i = 0; i < ca.length; i++) {
            freq[ca[i]]++;
        }

        Node root = buildTrie(freq);
        String[] st = new String[R];
        buildST(root, st, "");

        writeTrie(root);
        BinaryStdOut.write(ca.length);

        for (char c : ca) {
            String code = st[c];
            for (char bit : code.toCharArray()){
                if (bit == '0')
                    BinaryStdOut.write(false);
                else
                    BinaryStdOut.write(true);
            }
        }

        BinaryStdOut.close();
    }

    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch, 8);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    private static Node readTrie() {
        if (BinaryStdIn.readBoolean()) {
            char ch = BinaryStdIn.readChar(8);
            return new Node(ch, 0, null, null);
        }
        Node left = readTrie();
        Node right = readTrie();
        return new Node('\0', 0, left, right);
    }

    private static Node buildTrie(int[] freq) {
        PriorityQueue<Node> pq = new PriorityQueue();
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0)
                pq.add(new Node(c, freq[c], null, null));
        }

        while (pq.size() > 1) {
            Node a = pq.remove();
            Node b = pq.remove();
            pq.add(new Node('\0', a.freq + b.freq, a, b));
        }

        return pq.remove();
    }

    private static void buildST(Node node, String[] st, String prefix) {
        if (!node.isLeaf()) {
            buildST(node.left, st, prefix + "0");
            buildST(node.right, st, prefix + "1");
        }else{
            st[node.ch] = prefix;
        }
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
