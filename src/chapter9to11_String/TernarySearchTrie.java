package chapter9to11_String;

public class TernarySearchTrie<Value> {

    private class Node{
        char c;
        Value val;
        Node left, mid, right;
    }

    private Node root;

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, value, d);
        else if (c > x.c) x.right = put(x.right, key, value, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, value, d+1);
        else x.val = value;
        return x;
    }

    public Value get(String key){
        Node res = get(root, key, 0);
        if (res == null) return null;
        return res.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d+1);
        else return x;
    }

    public boolean contains(String key){
        return get(key) != null;
    }

    public static void main(String[] args) {
        TernarySearchTrie<Integer> trie = new TernarySearchTrie<>();
        trie.put("hat", 13);
        trie.put("hatat", 15);
        trie.put("hah", 3);
        trie.put("tst", 4);
        System.out.println(trie.get("hatat"));
        System.out.println(trie.get("hata"));
        System.out.println(trie.get("hah"));
        System.out.println(trie.get("tst"));
        System.out.println(trie.contains("haa"));
    }
}
