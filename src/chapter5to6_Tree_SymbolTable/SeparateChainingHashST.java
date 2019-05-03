package chapter5to6_Tree_SymbolTable;

public class SeparateChainingHashST<Key, Value> {

    private int M = 97;
    private Node[] st = new Node[M];

    private static class Node{
        Object key;
        Object value;
        Node next;

        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    Value get(Key key) {
        int h = hash(key);
        for (Node n = st[h]; n != null; n = n.next) {
            if (n.key.equals(key))
                return (Value) n.value;
        }
        return null;
    }

    void put(Key key, Value value) {
        int h = hash(key);
        for (Node n = st[h]; n != null; n = n.next) {
            if (key.equals(n.key)) {
                n.value = value;
                return;
            }
        }
        st[h] = new Node(key, value, st[h]);
    }

    public static void main(String[] args) {
        SeparateChainingHashST<Integer, String> st = new SeparateChainingHashST();
        st.put(7, "seven");
        st.put(11, "eleven");
        System.out.println(st.get(11));
        System.out.println(st.get(12));
        System.out.println(st.get(7));
        st.put(7, "what??");
        System.out.println(st.get(7));
    }

}
