package otherAlgo;

import java.util.ArrayList;
import java.util.List;

public class SegmentTree {

    private List<Integer> tree;
    private int n;

    public SegmentTree(int[] a) {
        n = a.length;
        tree = new ArrayList<>(n * 2);
        for (int i = 0; i < n; i++) tree.add(0);
        for (int i = 0; i < n; i++) tree.add(a[i]);
        for (int i = n - 1; i > 0; i--) tree.set(i, merge(tree.get(i*2), tree.get(i*2+1)));
    }

    // the reduce action can be sum / min / max, etc.
    private int merge(int a, int b){
        return a + b;
    }

    public void update(int i, int val) {
        i += n;
        tree.set(i, val);
        while (i > 1) {
            i /= 2;
            tree.set(i, merge(tree.get(i*2), tree.get(i*2+1)));
        }
    }

    // return result in [left, right)
    public int rangeQuery(int left, int right) {
        left += n;
        right += n;
        int res = 0;
        while (left < right) {
            if (left % 2 == 1){
                res = merge(res, tree.get(left));
                left++;
            }
            if (right % 2 == 1) {
                right--;
                res = merge(res, tree.get(right));
            }
            left /= 2;
            right /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6};
        SegmentTree tree = new SegmentTree(a);
        System.out.println(tree.rangeQuery(1, 5));
        tree.update(1, 12);
        System.out.println(tree.rangeQuery(1, 5));
    }
}
