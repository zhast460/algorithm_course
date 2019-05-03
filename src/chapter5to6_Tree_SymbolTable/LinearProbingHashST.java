package chapter5to6_Tree_SymbolTable;

public class LinearProbingHashST<Key, Value> {

    int M = 30001;
    Key[] keys = (Key[]) new Object[M];
    Value[] values = (Value[]) new Object[M];

    int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    void put(Key key, Value value) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                break;
        }
        keys[i] = key;
        values[i] = value;
    }

    Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    public static void main(String[] args) {
        LinearProbingHashST<Integer, String> st = new LinearProbingHashST();
        st.put(7, "seven");
        st.put(11, "eleven");
        System.out.println(st.get(11));
        System.out.println(st.get(12));
        System.out.println(st.get(7));
        st.put(7, "what??");
        System.out.println(st.get(7));
    }

}
