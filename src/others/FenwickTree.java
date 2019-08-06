package others;

// Or Binary Index Tree
public class FenwickTree {

    int[] tree;
    int[] a;

    public FenwickTree(int[] a) {
        this.a = a;
        tree = new int[a.length + 1];
        for (int i = 0; i < a.length; i++)
            tree[i+1] = a[i];
        for (int i = 1; i < tree.length; i++) {
            int j = i + (i & -i);
            if (j < tree.length) tree[j] += tree[i];
        }
    }

    public void update(int idx, int value){
        int delta = value - a[idx];
        a[idx] = value;
        idx++;
        while (idx < tree.length){
            tree[idx] += delta;
            idx = idx + (idx & -idx);
        }
    }

    public int prefixSum(int idx) {
        idx++;
        int res = 0;
        while (idx > 0) {
            res += tree[idx];
            idx = idx - (idx & -idx);
        }
        return res;
    }

    public int rangeSum(int from, int to) {
        return prefixSum(to) - prefixSum(from - 1);
    }

    public static void main(String[] args) {
        int[] a = {2,5,-1,3,0,8,-4,5};
        FenwickTree tree = new FenwickTree(a);
        System.out.println(tree.prefixSum(3));
        System.out.println(tree.prefixSum(6));
        System.out.println(tree.rangeSum(4, 7));
        tree.update(4, 5); // + 5
        System.out.println(tree.rangeSum(4, 7));
        tree.update(7, 3); // - 2
        System.out.println(tree.rangeSum(4, 7));
    }
}
