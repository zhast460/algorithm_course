package chapter7to8_Graph;

import java.util.HashMap;
import java.util.Map;

public class DisjointSetUnion {
    class Node{
        long data;
        Node parent;
        int rank;
    }

    private Map<Long, Node> map = new HashMap<>();

    public void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    public void union(long d1, long d2) {
        Node p1 = findSet(map.get(d1));
        Node p2 = findSet(map.get(d2));
        if (p1 == p2) return;
        if (p1.rank >= p2.rank){
            p1.rank = p1.rank == p2.rank ? p1.rank + 1 : p1.rank;
            p2.parent = p1;
        }else
            p1.parent = p2;
    }

    public long findSet(long d) {
        return findSet(map.get(d)).data;
    }

    private Node findSet(Node node) {
        if (node.parent != node) node.parent = findSet(node.parent);
        return node.parent;
    }

    public static void main(String[] args) {
        DisjointSetUnion ds = new DisjointSetUnion();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);
        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);
        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }
}
