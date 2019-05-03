package chapter9to11_String;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman_WithSimpleInOutput {

    // radix of extended ASCII
    private static final int R = 256;

    private static class Node implements Comparable<Node>{
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
        System.out.print("Please input trie: ");
        String s = new Scanner(System.in).nextLine();
        System.out.println();

        int[] idx = new int[]{0};
        Node root = readTrie(s, idx);

        System.out.print("Please input plaintext length: ");
        int num = new Scanner(System.in).nextInt();
        System.out.println();

        System.out.print("Please input code: ");
        s = new Scanner(System.in).nextLine();
        System.out.println();

        System.out.print("The plaintext is: ");
        int idx2 = 0;
        for (int i = 0; i < num; i++) {
            Node curr = root;
            while (!curr.isLeaf()){
                if(s.charAt(idx2++) == '1')
                    curr = curr.right;
                else
                    curr = curr.left;
            }
            System.out.print(curr.ch);
        }
        System.out.println();
    }

    public static void compress() {
        System.out.print("Please input plaintext: ");
        String input = new Scanner(System.in).nextLine();

        char[] ca = input.toCharArray();
        int[] freq = new int[R];

        for (int i = 0; i < ca.length; i++) {
            freq[ca[i]]++;
        }

        Node root = buildTrie(freq);
        String[] st = new String[R];
        buildST(root, st, "");

        System.out.print("Now writing trie: ");
        writeTrie(root);
        System.out.println();

        System.out.print("The plaintext length is: " + ca.length);
        System.out.println();

        System.out.print("The code is: ");
        for (char c : ca) {
            String code = st[c];
            System.out.print(code);
        }
        System.out.println();
    }

    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            System.out.print("1");
            System.out.print(leftPad0(x.ch, 8));
            return;
        }
        System.out.print("0");
        writeTrie(x.left);
        writeTrie(x.right);
    }

    private static Node readTrie(String s, int[] idx) {

        if (s.charAt(idx[0]++) == '1') {
            String sub = s.substring(idx[0], idx[0]+8);
            idx[0] += 8;
            char ch = (char) Integer.parseInt(sub, 2);
            return new Node(ch, 0, null, null);
        }
        Node left = readTrie(s, idx);
        Node right = readTrie(s, idx);
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

    public static String leftPad0(char c, int padToLength) {
        String s = Integer.toBinaryString(c);
        while (s.length() < padToLength)
            s = "0" + s;
        return s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sign = scanner.next();
        if      (sign.equals("-")) compress();
        else if (sign.equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
