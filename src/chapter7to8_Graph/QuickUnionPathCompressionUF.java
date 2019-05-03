package chapter7to8_Graph;

public class QuickUnionPathCompressionUF {

    private int[] parent;
    private int count;

    public QuickUnionPathCompressionUF(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    private int find(int v) {
        int root = v;
        while (parent[root] != root)
            root = parent[root];
        while (v != root){
            int p = parent[v];
            parent[v] = root;
            v = p;
        }
        return root;
    }

    public void union(int v, int w) {
        int rootV = find(v);
        int rootW = find(w);
        if (rootV == rootW) return;
        parent[rootV] = rootW;
        count--;
    }

    public boolean connected(int v, int w) {
        return find(v) == find(w);
    }

    public int size(){
        return count;
    }

    public static void main(String[] args) {
        QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(5);
        uf.union(0, 3);
        uf.union(1, 2);
        System.out.println(uf.size());
        System.out.println(uf.connected(2, 3));
        uf.union(1, 0);
        System.out.println(uf.connected(2, 3));
        System.out.println(uf.connected(0, 4));
    }
}
